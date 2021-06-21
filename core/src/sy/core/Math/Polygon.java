package sy.core.Math;


import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private List<Vector2> points;

    public List<Vector2> getPoints() {
        return points;
    }

    public Polygon(List<Vector2> points) {
        this.points = points;
    }

    public List<Vector2> filterInside(List<Vector2> points) {
        List<Vector2> ret = new ArrayList<>();
        for(Vector2 point : points) {
            if(isInsidePoly(point))
                ret.add(point);
        }
        return ret;
    }

    public boolean isOnSegment(Vector2 p, Vector2 q, Vector2 r) {
        return q.x <= Math.max(p.x, r.x) &&
                q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) &&
                q.y >= Math.min(p.y, r.y);
    }

    public int getOrientation(Vector2 p, Vector2 q, Vector2 r) {
        int val = (int)((q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y));
        if (val == 0)
            return 0;
        return (val > 0) ? 1 : 2;
    }

    public void shrink(float range) {
        Vector2 center = getCenter();
        for(int i = 0; i < points.size(); i++)
        {
            Vector2 currPoint = points.get(i);
            Vector2 direction = new Vector2(center.x - currPoint.x, center.y - currPoint.y).nor();
            Vector2 newPoint = new Vector2(currPoint.x + direction.x  * -range, currPoint.y + direction.y * -range);
            points.set(i, newPoint);
        }
    }

    public void moveBy(Vector2 direction, float distance) {
        Vector2 normDir = direction.nor();
        for(Vector2 point : points)
            point.set(point.x + normDir.x * distance, point.y + normDir.y * distance);
    }

    public boolean doIntersection(Vector2 p1, Vector2 q1,
                                  Vector2 p2, Vector2 q2)
    {
        int o1 = getOrientation(p1, q1, p2);
        int o2 = getOrientation(p1, q1, q2);
        int o3 = getOrientation(p2, q2, p1);
        int o4 = getOrientation(p2, q2, q1);

        if (o1 != o2 && o3 != o4)
        {
            return true;
        }

        // Special Cases
        if (o1 == 0 && isOnSegment(p1, p2, q1))
            return true;
        else if (o2 == 0 && isOnSegment(p1, q2, q1))
            return true;
        else if (o3 == 0 && isOnSegment(p2, p1, q2))
            return true;
        else if(o4 == 0 && isOnSegment(p2, q1, q2))
            return true;
        return false;
    }


    public boolean isInsidePoly(Vector2 p) {
        return isInsidePoly(p, 0);
    }

    public boolean isInsidePoly(Vector2 p, float inwards)
    {
        if (points.size() < 3)
            return false;
        Vector2 extreme = new Vector2(Float.MAX_VALUE, p.y);

        int count = 0, i = 0;
        do {
            int next = (i + 1) % points.size();

            if (doIntersection(points.get(i), points.get(next), p, extreme)) {
                if (getOrientation(points.get(i), p,  points.get(next)) == 0) {
                    return isOnSegment(points.get(i), p, points.get(next));
                }
                count++;
            }
            i = next;
        } while (i != 0);

        return (count % 2 == 1);
    }

    public Vector2 getCenter() {
        float newX = 0;
        float newY = 0;

        for (Vector2 point : points) {
            newX = newX + point.x;
            newY = newY + point.y;
        }
        return new Vector2(newX / points.size(), newY / points.size());
    }
}


