package sy.core.Math;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import sy.core.Globals;

public class PoissonDiskSampler {
    public static int DefaultPointsPerIteration = 30;
    private static float SquareRootTwo = (float)Math.sqrt(2);

    private static class Settings
    {
        public Vector2 TopLeft, LowerRight, Center;
        public Vector2 Dimensions;
        public float RejectionSqDistance;
        public float MinimumDistance;
        public float CellSize;
        public int GridWidth, GridHeight;

        public Settings(Vector2 topLeft, Vector2 lowerRight, Vector2 dimensions, Vector2 center, float cellSize, float minimumDistance, float rejectionSqDistance) {
            TopLeft = topLeft;
            LowerRight = lowerRight;
            Center = center;
            Dimensions = dimensions;
            RejectionSqDistance = rejectionSqDistance;
            MinimumDistance = minimumDistance;
            CellSize = cellSize;
        }
    }

    private static class State
    {
        public Vector2[][] Grid;
        public List<Vector2> ActivePoints = new ArrayList<>();
        public List<Vector2>  Points = new ArrayList<>();

        public State(Vector2[][] grid, List<Vector2> activePoints, List<Vector2> points) {
            Grid = grid;
            ActivePoints = activePoints;
            Points = points;
        }

        public State(Vector2[][] grid) {
            Grid = grid;
        }
    }

    public static List<Vector2> SampleCircle(Vector2 center, float radius, float minimumDistance)
    {
        return SampleCircle(center, radius, minimumDistance, DefaultPointsPerIteration);
    }
    public static List<Vector2> SampleCircle(Vector2 center, float radius, float minimumDistance, int pointsPerIteration)
    {
        return Sample(GoodMath.sub(center, new Vector2(radius, radius)), GoodMath.add(center, new Vector2(radius, radius)), radius, minimumDistance, pointsPerIteration);
    }

    public static List<Vector2> SampleRectangle(Vector2 topLeft, Vector2 lowerRight, float minimumDistance)
    {
        return SampleRectangle(topLeft, lowerRight, minimumDistance, DefaultPointsPerIteration);
    }
    public static List<Vector2> SampleRectangle(Vector2 topLeft, Vector2 lowerRight, float minimumDistance, int pointsPerIteration)
    {
        return Sample(topLeft, lowerRight, -1, minimumDistance, pointsPerIteration);
    }

    private static List<Vector2> Sample(Vector2 topLeft, Vector2 lowerRight, float rejectionDistance, float minimumDistance, int pointsPerIteration)
    {
        Settings settings = new Settings(topLeft, lowerRight, GoodMath.sub(lowerRight, topLeft),  GoodMath.div(GoodMath.add(topLeft, lowerRight), 2),
                minimumDistance / SquareRootTwo, minimumDistance, rejectionDistance == -1 ? -1 : rejectionDistance * rejectionDistance);
        settings.GridWidth = (int)(settings.Dimensions.x / settings.CellSize) + 1;
        settings.GridHeight = (int)(settings.Dimensions.y / settings.CellSize) + 1;
        State state = new State(new Vector2[settings.GridWidth][settings.GridHeight]);
        AddFirstPoint(settings, state);

        while (state.ActivePoints.size() != 0) {
            int listIndex = Globals.RANDOM.nextInt(state.ActivePoints.size());

            Vector2 point = state.ActivePoints.get(listIndex);
            boolean found = false;

            for (int k = 0; k < pointsPerIteration; k++)
                found |= AddNextPoint(point, settings, state);

            if (!found)
                state.ActivePoints.remove(listIndex);
        }

        return state.Points;
    }

    static void AddFirstPoint(Settings settings, State state)
    {
        boolean added = false;
        while (!added)
        {
            double d = Globals.RANDOM.nextDouble();
            double xr = settings.TopLeft.x + settings.Dimensions.x * d;

            d = Globals.RANDOM.nextDouble();
            double yr = settings.TopLeft.y + settings.Dimensions.y * d;

            Vector2 p = new Vector2((float)xr, (float)yr);
            if (settings.RejectionSqDistance != -1 &&
                    Vector2.dst2(settings.Center.x, settings.Center.y, p.x, p.y) > settings.RejectionSqDistance)
                continue;
            added = true;

            Vector2 index = Denormalize(p, settings.TopLeft, settings.CellSize);

            state.Grid[(int)index.x][(int)index.y] = p;
            state.ActivePoints.add(p);
            state.Points.add(p);
        }
    }

    private static boolean AddNextPoint(Vector2 point, Settings settings, State state)
    {
        boolean found = false;
        Vector2 q = GenerateRandomAround(point, settings.MinimumDistance);

        if (q.x >= settings.TopLeft.x && q.x < settings.LowerRight.x &&
                q.y > settings.TopLeft.y && q.y < settings.LowerRight.y &&
                (settings.RejectionSqDistance == -1 || Vector2.dst2(settings.Center.x, settings.Center.y, q.x, q.y) <= settings.RejectionSqDistance))
        {
            Vector2 qIndex = Denormalize(q, settings.TopLeft, settings.CellSize);
            boolean tooClose = false;

            for (int i = (int)Math.max(0, qIndex.x - 2); i < Math.min(settings.GridWidth, qIndex.x + 3) && !tooClose; i++)
                for (int j = (int)Math.max(0, qIndex.y - 2); j < Math.min(settings.GridHeight, qIndex.y + 3) && !tooClose; j++) {
                    Vector2 gridPos = state.Grid[i][j];
                    if (state.Grid[i][j] != null && Vector2.dst(gridPos.x, gridPos.y, q.x, q.y) < settings.MinimumDistance)
                        tooClose = true;
                }

            if (!tooClose)
            {
                found = true;
                state.ActivePoints.add(q);
                state.Points.add(q);
                state.Grid[(int)qIndex.x][(int)qIndex.y] = q;
            }
        }
        return found;
    }

    private static Vector2 GenerateRandomAround(Vector2 center, float minimumDistance)
    {
        double d = Globals.RANDOM.nextDouble();
        double radius = minimumDistance + minimumDistance * d;

        d = Globals.RANDOM.nextDouble();
        double angle = MathHelper.TwoPi * d;

        float newX = (float) (radius * Math.sin(angle));
        float newY = (float) (radius * Math.cos(angle));

        return new Vector2((float)(center.x + newX), (float)(center.y + newY));
    }

    static Vector2 Denormalize(Vector2 point, Vector2 origin, double cellSize)
    {
        return new Vector2((int)((point.x - origin.x) / cellSize), (int)((point.y - origin.y) / cellSize));
    }

    public static class MathHelper
    {
        public static float Pi = (float)Math.PI;
        public static float HalfPi = (float)(Math.PI / 2);
        public static float TwoPi = (float)(Math.PI * 2);
    }

}

