package sy.gameObjects;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


import sy.Game;
import sy.core.MapNode;
import sy.core.MoveType;
import sy.core.NodeGraph;
import sy.rendering.RenderPipeline;

public class NodeGraphObject extends GameObject {
    private NodeGraph graph;
    //ToDo: scene2d or self made transform hierarchy, if this obejct moves, nodes do not move
    ArrayList<Vector2> nodeposition;

    public NodeGraphObject(String uuid) {
        super(uuid);
        nodeposition = new ArrayList<>();

        //1-10
        nodeposition.add(new Vector2(293, 1827));
        nodeposition.add(new Vector2(761, 1861));
        nodeposition.add(new Vector2(1058, 1855));
        nodeposition.add(new Vector2(1240, 1865));
        nodeposition.add(new Vector2(1974, 1838));
        nodeposition.add(new Vector2(2201, 1847));
        nodeposition.add(new Vector2(2416, 1818));
        nodeposition.add(new Vector2(190, 1718));
        nodeposition.add(new Vector2(379, 1713));
        nodeposition.add(new Vector2(908, 1720));

        //11-20
        nodeposition.add(new Vector2(1051, 1699));
        nodeposition.add(new Vector2(1163, 1726));
        nodeposition.add(new Vector2(1375, 1730));
        nodeposition.add(new Vector2(1586, 1775));
        nodeposition.add(new Vector2(1808, 1798));
        nodeposition.add(new Vector2(2015, 1720));
        nodeposition.add(new Vector2(2399, 1645));
        nodeposition.add(new Vector2(86, 1635));
        nodeposition.add(new Vector2(266, 1609));
        nodeposition.add(new Vector2(479, 1669));

        //21-30
        nodeposition.add(new Vector2(702, 1572));
        nodeposition.add(new Vector2(1054, 1546));
        nodeposition.add(new Vector2(1223, 1630));
        nodeposition.add(new Vector2(1497, 1616));
        nodeposition.add(new Vector2(1617, 1594));
        nodeposition.add(new Vector2(1793, 1724));
        nodeposition.add(new Vector2(1823, 1614));
        nodeposition.add(new Vector2(1898, 1663));
        nodeposition.add(new Vector2(2199, 1597));
        nodeposition.add(new Vector2(2480, 1611));

        //31-40
        nodeposition.add(new Vector2(160, 1553));
        nodeposition.add(new Vector2(417, 1490));
        nodeposition.add(new Vector2(603, 1524));
        nodeposition.add(new Vector2(928, 1488));
        nodeposition.add(new Vector2(1113, 1445));
        nodeposition.add(new Vector2(1187, 1436));
        nodeposition.add(new Vector2(1305, 1533));
        nodeposition.add(new Vector2(1548, 1522));
        nodeposition.add(new Vector2(1672, 1545));
        nodeposition.add(new Vector2(1871, 1462));

        //41-50
        nodeposition.add(new Vector2(1951, 1493));
        nodeposition.add(new Vector2(2405, 1498));
        nodeposition.add(new Vector2(34, 1458));
        nodeposition.add(new Vector2(290, 1402));
        nodeposition.add(new Vector2(478, 1377));
        nodeposition.add(new Vector2(637, 1421));
        nodeposition.add(new Vector2(760, 1458));
        nodeposition.add(new Vector2(955, 1367));
        nodeposition.add(new Vector2(1256, 1362));
        nodeposition.add(new Vector2(1379, 1433));

        //51-60
        nodeposition.add(new Vector2(1625, 1416));
        nodeposition.add(new Vector2(1748, 1439));
        nodeposition.add(new Vector2(1882, 1364));
        nodeposition.add(new Vector2(1985, 1400));
        nodeposition.add(new Vector2(2206, 1397));
        nodeposition.add(new Vector2(2487, 1377));
        nodeposition.add(new Vector2(128, 1356));
        nodeposition.add(new Vector2(375, 1330));
        nodeposition.add(new Vector2(420, 1269));
        nodeposition.add(new Vector2(520, 1287));

        //61-70
        nodeposition.add(new Vector2(679, 1262));
        nodeposition.add(new Vector2(768, 1288));
        nodeposition.add(new Vector2(979, 1186));
        nodeposition.add(new Vector2(1105, 1211));
        nodeposition.add(new Vector2(1238, 1238));
        nodeposition.add(new Vector2(1324, 1251));
        nodeposition.add(new Vector2(1468, 1280));
        nodeposition.add(new Vector2(1642, 1312));
        nodeposition.add(new Vector2(1797, 1327));
        nodeposition.add(new Vector2(1988, 1293));

        //71-80
        nodeposition.add(new Vector2(2175, 1295));
        nodeposition.add(new Vector2(2362, 1274));
        nodeposition.add(new Vector2(129, 1256));
        nodeposition.add(new Vector2(214, 1161));
        nodeposition.add(new Vector2(331, 1188));
        nodeposition.add(new Vector2(483, 1201));
        nodeposition.add(new Vector2(579, 1114));
        nodeposition.add(new Vector2(719, 1129));
        nodeposition.add(new Vector2(805, 1156));
        nodeposition.add(new Vector2(1014, 1104));

        //81-90
        nodeposition.add(new Vector2(1203, 1082));
        nodeposition.add(new Vector2(1272, 1124));
        nodeposition.add(new Vector2(1436, 1150));
        nodeposition.add(new Vector2(1554, 1202));
        nodeposition.add(new Vector2(1652, 1242));
        nodeposition.add(new Vector2(1826, 1169));
        nodeposition.add(new Vector2(2001, 1125));
        nodeposition.add(new Vector2(2089, 1110));
        nodeposition.add(new Vector2(2164, 1170));
        nodeposition.add(new Vector2(2282, 1167));

        //91-100
        nodeposition.add(new Vector2(2467, 1157));
        nodeposition.add(new Vector2(58, 1064));
        nodeposition.add(new Vector2(69, 1000));
        nodeposition.add(new Vector2(225, 1029));
        nodeposition.add(new Vector2(307, 1048));
        nodeposition.add(new Vector2(683, 1006));
        nodeposition.add(new Vector2(750, 1021));
        nodeposition.add(new Vector2(855, 1056));
        nodeposition.add(new Vector2(961, 1047));
        nodeposition.add(new Vector2(1132, 983));

        //101-110
        nodeposition.add(new Vector2(1311, 1054));
        nodeposition.add(new Vector2(1538, 1136));
        nodeposition.add(new Vector2(1675, 1163));
        nodeposition.add(new Vector2(1829, 1091));
        nodeposition.add(new Vector2(2233, 1057));
        nodeposition.add(new Vector2(2381, 1033));
        nodeposition.add(new Vector2(2499, 1024));
        nodeposition.add(new Vector2(2192, 888));
        nodeposition.add(new Vector2(788, 871));
        nodeposition.add(new Vector2(915, 982));

        //111-120
        nodeposition.add(new Vector2(984, 893));
        nodeposition.add(new Vector2(1037, 931));
        nodeposition.add(new Vector2(1205, 920));
        nodeposition.add(new Vector2(1325, 963));
        nodeposition.add(new Vector2(1534, 1018));
        nodeposition.add(new Vector2(1830, 921));
        nodeposition.add(new Vector2(2029, 845));
        nodeposition.add(new Vector2(1835, 790));
        nodeposition.add(new Vector2(2447, 760));
        nodeposition.add(new Vector2(57, 685));

        //121-130
        nodeposition.add(new Vector2(146, 675));
        nodeposition.add(new Vector2(270, 686));
        nodeposition.add(new Vector2(581, 692));
        nodeposition.add(new Vector2(760, 732));
        nodeposition.add(new Vector2(1088, 843));
        nodeposition.add(new Vector2(1411, 893));
        nodeposition.add(new Vector2(1650, 832));
        nodeposition.add(new Vector2(1944, 489));
        nodeposition.add(new Vector2(2022, 770));
        nodeposition.add(new Vector2(1041, 725));

        //131-140
        nodeposition.add(new Vector2(1131, 782));
        nodeposition.add(new Vector2(1341, 788));
        nodeposition.add(new Vector2(1555, 688));
        nodeposition.add(new Vector2(1722, 739));
        nodeposition.add(new Vector2(2091, 707));
        nodeposition.add(new Vector2(2380, 592));
        nodeposition.add(new Vector2(475, 591));
        nodeposition.add(new Vector2(820, 659));
        nodeposition.add(new Vector2(1022, 652));
        nodeposition.add(new Vector2(1332, 671));

        //141-150
        nodeposition.add(new Vector2(1621, 651));
        nodeposition.add(new Vector2(1828, 609));
        nodeposition.add(new Vector2(2013, 636));
        nodeposition.add(new Vector2(82, 458));
        nodeposition.add(new Vector2(174, 475));
        nodeposition.add(new Vector2(291, 476));
        nodeposition.add(new Vector2(382, 501));
        nodeposition.add(new Vector2(510, 525));
        nodeposition.add(new Vector2(622, 539));
        nodeposition.add(new Vector2(745, 584));

        //151-160
        nodeposition.add(new Vector2(802, 521));
        nodeposition.add(new Vector2(887, 581));
        nodeposition.add(new Vector2(936, 507));
        nodeposition.add(new Vector2(1140, 557));
        nodeposition.add(new Vector2(1223, 460));
        nodeposition.add(new Vector2(1357, 453));
        nodeposition.add(new Vector2(1483, 454));
        nodeposition.add(new Vector2(1683, 535));
        nodeposition.add(new Vector2(1692, 259));
        nodeposition.add(new Vector2(2103, 458));

        //161-170
        nodeposition.add(new Vector2(2273, 479));
        nodeposition.add(new Vector2(2504, 476));
        nodeposition.add(new Vector2(286, 412));
        nodeposition.add(new Vector2(415, 417));
        nodeposition.add(new Vector2(650, 379));
        nodeposition.add(new Vector2(901, 431));
        nodeposition.add(new Vector2(1098, 397));
        nodeposition.add(new Vector2(1158, 340));
        nodeposition.add(new Vector2(1354, 354));
        nodeposition.add(new Vector2(1470, 346));

        //171-180
        nodeposition.add(new Vector2(2222, 81));
        nodeposition.add(new Vector2(1879, 352));
        nodeposition.add(new Vector2(2148, 277));
        nodeposition.add(new Vector2(2359, 347));
        nodeposition.add(new Vector2(2478, 253));
        nodeposition.add(new Vector2(46, 294));
        nodeposition.add(new Vector2(150, 316));
        nodeposition.add(new Vector2(332, 329));
        nodeposition.add(new Vector2(555, 301));
        nodeposition.add(new Vector2(692, 279));

        //181-190
        nodeposition.add(new Vector2(831, 311));
        nodeposition.add(new Vector2(909, 299));
        nodeposition.add(new Vector2(1038, 359));
        nodeposition.add(new Vector2(1284, 253));
        nodeposition.add(new Vector2(1424, 153));
        nodeposition.add(new Vector2(1580, 188));
        nodeposition.add(new Vector2(1823, 235));
        nodeposition.add(new Vector2(2047, 239));
        nodeposition.add(new Vector2(148, 141));
        nodeposition.add(new Vector2(267, 73));

        //191-199
        nodeposition.add(new Vector2(415, 193));
        nodeposition.add(new Vector2(448, 43));
        nodeposition.add(new Vector2(794, 183));
        nodeposition.add(new Vector2(833, 134));
        nodeposition.add(new Vector2(938, 137));
        nodeposition.add(new Vector2(1088, 219));
        nodeposition.add(new Vector2(1110, 117));
        nodeposition.add(new Vector2(1688, 30));
        nodeposition.add(new Vector2(2077, 44));

        graph = new NodeGraph(nodeposition);

        //Edges from node 1
        graph.addEdge(0, 7, MoveType.TAXI);
        graph.addEdge(0, 8, MoveType.TAXI);
        graph.addEdge(0, 57, MoveType.BUS);
        graph.addEdge(0, 45, MoveType.BUS);
        graph.addEdge(0, 45, MoveType.UBAHN);
        // graph.addEdge(0,45, allowsMove2Move3);

        //Edges from node 2
        graph.addEdge(1, 9, MoveType.TAXI);
        graph.addEdge(1, 19, MoveType.TAXI);

        //Edges from node 3
        graph.addEdge(2, 3, MoveType.TAXI);
        graph.addEdge(2, 11, MoveType.TAXI);
        graph.addEdge(2, 10, MoveType.TAXI);
        graph.addEdge(2, 21, MoveType.BUS);
        graph.addEdge(2, 22, MoveType.BUS);

        //Edges from node 4
        graph.addEdge(3, 12, MoveType.TAXI);

        //Edges from node 5
        graph.addEdge(4, 14, MoveType.TAXI);
        graph.addEdge(4, 15, MoveType.TAXI);

        //Edges from node 6
        graph.addEdge(5, 6, MoveType.TAXI);
        graph.addEdge(5, 28, MoveType.TAXI);

        //Edges from node 7
        graph.addEdge(6, 16, MoveType.TAXI);
        graph.addEdge(6, 41, MoveType.BUS);

        //Edges from node 8
        graph.addEdge(7, 17, MoveType.TAXI);
        graph.addEdge(7, 18, MoveType.TAXI);

        //Edges from node 9
        graph.addEdge(8, 18, MoveType.TAXI);
        graph.addEdge(8, 19, MoveType.TAXI);

        //Edges from node 10
        graph.addEdge(9, 20, MoveType.TAXI);
        graph.addEdge(9, 33, MoveType.TAXI);
        graph.addEdge(9, 17, MoveType.TAXI);

        //Edges from node 11
        graph.addEdge(10, 21, MoveType.TAXI);

        //Edges from node 12
        graph.addEdge(11, 22, MoveType.TAXI);

        //Edges from node 13
        graph.addEdge(12, 22, MoveType.TAXI);
        graph.addEdge(12, 22, MoveType.BUS);
        graph.addEdge(12, 45, MoveType.UBAHN);
        graph.addEdge(12, 66, MoveType.UBAHN);
        graph.addEdge(12, 88, MoveType.UBAHN);
        graph.addEdge(12, 23, MoveType.TAXI);
        graph.addEdge(12, 13, MoveType.BUS);
        graph.addEdge(12, 24, MoveType.BUS);

        //Edges from node 14
        graph.addEdge(13, 24, MoveType.TAXI);
        graph.addEdge(13, 14, MoveType.BUS);
        graph.addEdge(13, 14, MoveType.TAXI);

        //Edges from node 15
        graph.addEdge(14, 15, MoveType.TAXI);
        graph.addEdge(14, 27, MoveType.TAXI);
        graph.addEdge(14, 25, MoveType.TAXI);
        graph.addEdge(14, 40, MoveType.TAXI);

        //Edges from node 16
        graph.addEdge(15, 27, MoveType.TAXI);
        graph.addEdge(15, 28, MoveType.TAXI);

        //Edges from node 17
        graph.addEdge(16, 28, MoveType.TAXI);
        graph.addEdge(16, 29, MoveType.TAXI);
        graph.addEdge(16, 41, MoveType.TAXI);

        //Edges from node 18
        graph.addEdge(17, 30, MoveType.TAXI);
        graph.addEdge(17, 42, MoveType.TAXI);

        //Edges from node 19
        graph.addEdge(18, 31, MoveType.TAXI);

        //Edges from node 20
        graph.addEdge(19, 32, MoveType.TAXI);

        //Edges from node 21
        graph.addEdge(20, 32, MoveType.TAXI);

        //Edges from node 22
        graph.addEdge(21, 22, MoveType.TAXI);
        graph.addEdge(21, 22, MoveType.BUS);
        graph.addEdge(21, 33, MoveType.TAXI);
        graph.addEdge(21, 33, MoveType.BUS);
        graph.addEdge(21, 34, MoveType.TAXI);
        graph.addEdge(21, 64, MoveType.BUS);

        //Edges from node 23
        graph.addEdge(22, 36, MoveType.TAXI);
        graph.addEdge(22, 66, MoveType.BUS);

        //Edges from node 24
        graph.addEdge(23, 36, MoveType.TAXI);
        graph.addEdge(23, 37, MoveType.TAXI);

        //Edges from node 25
        graph.addEdge(24, 37, MoveType.TAXI);
        graph.addEdge(24, 38, MoveType.TAXI);

        //Edges from node 26
        graph.addEdge(25, 26, MoveType.TAXI);
        graph.addEdge(25, 38, MoveType.TAXI);

        //Edges from node 27
        graph.addEdge(26, 27, MoveType.TAXI);
        graph.addEdge(26, 39, MoveType.TAXI);

        //Edges from node 29
        graph.addEdge(28, 40, MoveType.TAXI);
        graph.addEdge(28, 40, MoveType.BUS);
        graph.addEdge(28, 41, MoveType.TAXI);
        graph.addEdge(28, 41, MoveType.BUS);
        graph.addEdge(28, 54, MoveType.TAXI);
        graph.addEdge(28, 54, MoveType.BUS);

        //Edges from node 30
        graph.addEdge(29, 41, MoveType.TAXI);

        //Edges from node 31
        graph.addEdge(30, 42, MoveType.TAXI);
        graph.addEdge(30, 43, MoveType.TAXI);

        //Edges from node 32
        graph.addEdge(31, 43, MoveType.TAXI);
        graph.addEdge(31, 32, MoveType.TAXI);
        graph.addEdge(31, 44, MoveType.TAXI);

        //Edges from node 34
        graph.addEdge(33, 45, MoveType.BUS);
        graph.addEdge(33, 62, MoveType.BUS);
        graph.addEdge(33, 46, MoveType.TAXI);
        graph.addEdge(33, 47, MoveType.TAXI);

        //edges from node 35
        graph.addEdge(34, 64, MoveType.TAXI);
        graph.addEdge(34, 64, MoveType.BUS);
        graph.addEdge(34, 47, MoveType.TAXI);
        graph.addEdge(34, 35, MoveType.TAXI);

        //edges from node 36
        graph.addEdge(35, 36, MoveType.TAXI);
        graph.addEdge(35, 48, MoveType.TAXI);

        //edges from node 37
        graph.addEdge(36, 49, MoveType.TAXI);

        //edges from node 38
        graph.addEdge(37, 50, MoveType.TAXI);

        //edges from node 39
        graph.addEdge(38, 50, MoveType.TAXI);
        graph.addEdge(38, 51, MoveType.TAXI);

        //edges from node 40
        graph.addEdge(39, 52, MoveType.TAXI);
        graph.addEdge(39, 51, MoveType.TAXI);
        graph.addEdge(39, 40, MoveType.TAXI);

        //edges from node 41
        graph.addEdge(40, 53, MoveType.TAXI);
        graph.addEdge(40, 51, MoveType.BUS);
        graph.addEdge(40, 86, MoveType.BUS);

        //edges from node 42
        graph.addEdge(41, 55, MoveType.TAXI);
        graph.addEdge(41, 71, MoveType.TAXI);
        graph.addEdge(41, 71, MoveType.BUS);

        //edges from node 43
        graph.addEdge(42, 56, MoveType.TAXI);

        //edges from node 44
        graph.addEdge(43, 57, MoveType.TAXI);

        //edges from node 45
        graph.addEdge(44, 57, MoveType.TAXI);
        graph.addEdge(44, 58, MoveType.TAXI);
        graph.addEdge(44, 59, MoveType.TAXI);
        graph.addEdge(44, 45, MoveType.TAXI);

        //edges from node 46
        graph.addEdge(45, 60, MoveType.TAXI);
        graph.addEdge(45, 56, MoveType.TAXI);
        graph.addEdge(45, 57, MoveType.BUS);
        graph.addEdge(45, 77, MoveType.BUS);
        graph.addEdge(45, 73, MoveType.UBAHN);
        graph.addEdge(45, 78, MoveType.UBAHN);

        //edges from node 47
        graph.addEdge(46, 61, MoveType.TAXI);

        //edges from node 48
        graph.addEdge(47, 61, MoveType.TAXI);
        graph.addEdge(47, 62, MoveType.TAXI);

        //edges from node 49
        graph.addEdge(48, 49, MoveType.TAXI);
        graph.addEdge(48, 65, MoveType.TAXI);

        //edges from node 50
        graph.addEdge(49, 66, MoveType.TAXI);

        //edges from node 51
        graph.addEdge(50, 51, MoveType.TAXI);
        graph.addEdge(50, 67, MoveType.TAXI);
        graph.addEdge(50, 66, MoveType.TAXI);

        //edges from node 52
        graph.addEdge(51, 68, MoveType.TAXI);
        graph.addEdge(51, 66, MoveType.BUS);
        graph.addEdge(51, 85, MoveType.BUS);

        //edges from node 53
        graph.addEdge(52, 68, MoveType.TAXI);
        graph.addEdge(52, 53, MoveType.TAXI);

        //edges from node 54
        graph.addEdge(52, 68, MoveType.TAXI);

        //edges from node 55
        graph.addEdge(54, 70, MoveType.TAXI);
        graph.addEdge(54, 88, MoveType.BUS);

        //edges from node 56
        graph.addEdge(55, 90, MoveType.TAXI);

        //edges from node 57
        graph.addEdge(56, 57, MoveType.TAXI);
        graph.addEdge(56, 72, MoveType.TAXI);

        //edges from node 58
        graph.addEdge(57, 58, MoveType.TAXI);
        graph.addEdge(57, 76, MoveType.BUS);
        graph.addEdge(57, 58, MoveType.TAXI);
        graph.addEdge(57, 73, MoveType.TAXI);
        graph.addEdge(57, 73, MoveType.BUS);
        graph.addEdge(57, 73, MoveType.UBAHN);

        //edges from node 59
        graph.addEdge(58, 75, MoveType.TAXI);
        graph.addEdge(58, 74, MoveType.TAXI);

        //edges from node 60
        graph.addEdge(59, 60, MoveType.TAXI);
        graph.addEdge(59, 75, MoveType.TAXI);

        //edges from node 61
        graph.addEdge(60, 75, MoveType.TAXI);
        graph.addEdge(60, 77, MoveType.TAXI);
        graph.addEdge(60, 61, MoveType.TAXI);

        //edges from node 62
        graph.addEdge(61, 78, MoveType.TAXI);

        //edges from node 63
        graph.addEdge(62, 63, MoveType.TAXI);
        graph.addEdge(62, 64, MoveType.BUS);
        graph.addEdge(62, 79, MoveType.TAXI);
        graph.addEdge(62, 99, MoveType.BUS);
        graph.addEdge(62, 78, MoveType.TAXI);
        graph.addEdge(62, 78, MoveType.BUS);
        graph.addEdge(62, 78, MoveType.UBAHN);

        //edges from node 64
        graph.addEdge(63, 64, MoveType.TAXI);
        graph.addEdge(63, 80, MoveType.TAXI);

        //edges from node 65
        graph.addEdge(64, 65, MoveType.TAXI);
        graph.addEdge(64, 81, MoveType.TAXI);
        graph.addEdge(64, 81, MoveType.BUS);

        //edges from node 66
        graph.addEdge(65, 81, MoveType.TAXI);
        graph.addEdge(65, 66, MoveType.TAXI);

        //edges from node 67
        graph.addEdge(66, 67, MoveType.TAXI);
        graph.addEdge(66, 83, MoveType.TAXI);
        graph.addEdge(66, 78, MoveType.UBAHN);
        graph.addEdge(66, 110, MoveType.UBAHN);
        graph.addEdge(66, 88, MoveType.UBAHN);
        graph.addEdge(66, 101, MoveType.BUS);

        //edges from node 68
        graph.addEdge(67, 68, MoveType.TAXI);
        graph.addEdge(67, 84, MoveType.TAXI);

        //edges from node 69
        graph.addEdge(68, 85, MoveType.TAXI);

        //edges from node 70
        graph.addEdge(69, 70, MoveType.TAXI);
        graph.addEdge(69, 86, MoveType.TAXI);

        //edges from node 71
        graph.addEdge(70, 88, MoveType.TAXI);
        graph.addEdge(70, 71, MoveType.TAXI);

        //edges from node 72
        graph.addEdge(71, 90, MoveType.TAXI);
        graph.addEdge(71, 89, MoveType.TAXI);
        graph.addEdge(71, 104, MoveType.BUS);
        graph.addEdge(71, 106, MoveType.BUS);

        //edges from node 73
        graph.addEdge(72, 73, MoveType.TAXI);
        graph.addEdge(72, 91, MoveType.TAXI);

        //edges from node 74
        graph.addEdge(73, 91, MoveType.TAXI);
        graph.addEdge(73, 93, MoveType.BUS);
        graph.addEdge(73, 74, MoveType.TAXI);

        //edges from node 75
        graph.addEdge(74, 93, MoveType.TAXI);

        //edges from node 76
        graph.addEdge(75, 76, MoveType.TAXI);

        //edges from node 77
        graph.addEdge(76, 93, MoveType.BUS);
        graph.addEdge(76, 94, MoveType.TAXI);
        graph.addEdge(76, 77, MoveType.BUS);
        graph.addEdge(76, 77, MoveType.TAXI);
        graph.addEdge(76, 95, MoveType.TAXI);
        graph.addEdge(76, 123, MoveType.BUS);

        //edges from node 78
        graph.addEdge(77, 96, MoveType.TAXI);
        graph.addEdge(77, 78, MoveType.TAXI);
        graph.addEdge(77, 79, MoveType.BUS);

        //edges from node 79
        graph.addEdge(78, 97, MoveType.TAXI);
        graph.addEdge(78, 110, MoveType.UBAHN);

        //edges from node 80
        graph.addEdge(79, 99, MoveType.TAXI);
        graph.addEdge(79, 98, MoveType.TAXI);

        //edges from node 81
        graph.addEdge(80, 81, MoveType.TAXI);
        graph.addEdge(80, 99, MoveType.TAXI);

        //edges from node 82
        graph.addEdge(81, 100, MoveType.TAXI);
        graph.addEdge(81, 139, MoveType.BUS);
        graph.addEdge(81, 99, MoveType.BUS);

        //edges from node 83
        graph.addEdge(82, 100, MoveType.TAXI);
        graph.addEdge(82, 101, MoveType.TAXI);

        //edges from node 84
        graph.addEdge(83, 84, MoveType.TAXI);

        //edges from node 85
        graph.addEdge(84, 102, MoveType.TAXI);

        //edges from node 86
        graph.addEdge(85, 102, MoveType.TAXI);
        graph.addEdge(85, 101, MoveType.BUS);
        graph.addEdge(85, 103, MoveType.TAXI);
        graph.addEdge(85, 115, MoveType.BUS);
        graph.addEdge(86, 115, MoveType.BUS);
        graph.addEdge(86, 115, MoveType.TAXI);

        //edges from node 87
        graph.addEdge(86, 87, MoveType.TAXI);
        graph.addEdge(86, 104, MoveType.BUS);

        //edges from node 88
        graph.addEdge(87, 88, MoveType.TAXI);
        graph.addEdge(87, 116, MoveType.TAXI);

        //edges from node 89
        graph.addEdge(88, 104, MoveType.TAXI);
        graph.addEdge(88, 104, MoveType.BUS);
        graph.addEdge(88, 127, MoveType.UBAHN);

        //edges from node 90
        graph.addEdge(89, 104, MoveType.TAXI);

        //edges from node 91
        graph.addEdge(90, 106, MoveType.TAXI);
        graph.addEdge(90, 104, MoveType.TAXI);

        //edges from node 92
        graph.addEdge(91, 92, MoveType.TAXI);

        //edges from node 93
        graph.addEdge(92, 93, MoveType.TAXI);
        graph.addEdge(92, 93, MoveType.BUS);

        //edges from node 94
        graph.addEdge(93, 94, MoveType.TAXI);

        //edges from node 95
        graph.addEdge(94, 121, MoveType.TAXI);

        //edges from node 96
        graph.addEdge(95, 96, MoveType.TAXI);
        graph.addEdge(95, 108, MoveType.TAXI);

        //edges from node 97
        graph.addEdge(96, 97, MoveType.TAXI);
        graph.addEdge(96, 108, MoveType.TAXI);

        //edges from node 98
        graph.addEdge(97, 109, MoveType.TAXI);
        graph.addEdge(97, 98, MoveType.TAXI);

        //edges from node 99
        graph.addEdge(98, 109, MoveType.TAXI);
        graph.addEdge(98, 111, MoveType.TAXI);

        //edges from node 100
        graph.addEdge(99, 111, MoveType.TAXI);
        graph.addEdge(99, 110, MoveType.BUS);
        graph.addEdge(99, 100, MoveType.TAXI);
        graph.addEdge(99, 112, MoveType.TAXI);

        //edges from node 101
        graph.addEdge(100, 113, MoveType.TAXI);

        //edges from node 102
        graph.addEdge(101, 102, MoveType.TAXI);
        graph.addEdge(101, 114, MoveType.TAXI);
        graph.addEdge(101, 126, MoveType.BUS);

        //edges from node 104
        graph.addEdge(103, 115, MoveType.TAXI);

        //edges from node 105
        graph.addEdge(104, 105, MoveType.TAXI);
        graph.addEdge(104, 106, MoveType.BUS);
        graph.addEdge(104, 107, MoveType.TAXI);
        graph.addEdge(104, 107, MoveType.BUS);

        //edges from node 106
        graph.addEdge(105, 106, MoveType.TAXI);

        //edges from node 107
        graph.addEdge(106, 118, MoveType.TAXI);
        graph.addEdge(106, 160, MoveType.BUS);

        //edges from node 108
        graph.addEdge(107, 118, MoveType.TAXI);
        graph.addEdge(107, 134, MoveType.BUS);
        graph.addEdge(107, 115, MoveType.BUS);
        graph.addEdge(107, 116, MoveType.TAXI);

        //edges from node 109
        graph.addEdge(108, 109, MoveType.TAXI);
        graph.addEdge(108, 123, MoveType.TAXI);

        //edges from node 110
        graph.addEdge(109, 110, MoveType.TAXI);

        //edges from node 111
        graph.addEdge(110, 162, MoveType.UBAHN);
        graph.addEdge(110, 152, MoveType.UBAHN);
        graph.addEdge(110, 123, MoveType.TAXI);
        graph.addEdge(110, 123, MoveType.BUS);
        graph.addEdge(110, 111, MoveType.TAXI);

        //edges from node 112
        graph.addEdge(111, 124, MoveType.TAXI);

        //edges from node 113
        graph.addEdge(112, 124, MoveType.TAXI);
        graph.addEdge(112, 113, MoveType.TAXI);

        //edges from node 114
        graph.addEdge(113, 125, MoveType.TAXI);
        graph.addEdge(113, 131, MoveType.TAXI);

        //edges from node 115
        graph.addEdge(114, 125, MoveType.TAXI);
        graph.addEdge(114, 126, MoveType.TAXI);

        //edges from node 116
        graph.addEdge(115, 126, MoveType.TAXI);
        graph.addEdge(115, 126, MoveType.BUS);
        graph.addEdge(115, 117, MoveType.TAXI);
        graph.addEdge(115, 141, MoveType.BUS);
        graph.addEdge(115, 116, MoveType.TAXI);

        //edges from node 117
        graph.addEdge(116, 128, MoveType.TAXI);

        //edges from node 118
        graph.addEdge(117, 133, MoveType.TAXI);
        graph.addEdge(117, 141, MoveType.TAXI);
        graph.addEdge(117, 128, MoveType.TAXI);

        //edges from node 119
        graph.addEdge(118, 135, MoveType.TAXI);

        //edges from node 120
        graph.addEdge(119, 120, MoveType.TAXI);
        graph.addEdge(119, 143, MoveType.TAXI);

        //edges from node 121
        graph.addEdge(120, 122, MoveType.TAXI);
        graph.addEdge(120, 144, MoveType.TAXI);

        //edges from node 122
        graph.addEdge(121, 122, MoveType.TAXI);
        graph.addEdge(121, 122, MoveType.BUS);
        graph.addEdge(121, 145, MoveType.TAXI);

        //edges from node 123
        graph.addEdge(122, 123, MoveType.TAXI);
        graph.addEdge(122, 123, MoveType.BUS);
        graph.addEdge(122, 136, MoveType.TAXI);
        graph.addEdge(122, 143, MoveType.BUS);
        graph.addEdge(122, 147, MoveType.TAXI);
        graph.addEdge(122, 148, MoveType.TAXI);
        graph.addEdge(122, 164, MoveType.BUS);

        //edges from node 124
        graph.addEdge(123, 137, MoveType.TAXI);
        graph.addEdge(123, 152, MoveType.BUS);

        //edges from node 125
        graph.addEdge(124, 130, MoveType.TAXI);

        //edges from node 126
        graph.addEdge(125, 125, MoveType.TAXI);
        graph.addEdge(125, 139, MoveType.TAXI);

        //edges from node 127
        graph.addEdge(126, 132, MoveType.TAXI);
        graph.addEdge(126, 132, MoveType.BUS);
        graph.addEdge(126, 133, MoveType.TAXI);

        //edges from node 128
        graph.addEdge(127, 141, MoveType.BUS);
        graph.addEdge(127, 141, MoveType.TAXI);
        graph.addEdge(127, 142, MoveType.TAXI);
        graph.addEdge(127, 134, MoveType.BUS);
        graph.addEdge(127, 159, MoveType.TAXI);
        graph.addEdge(127, 150, MoveType.BUS);
        graph.addEdge(127, 186, MoveType.BUS);
        graph.addEdge(127, 171, MoveType.TAXI);
        graph.addEdge(127, 184, MoveType.UBAHN);
        graph.addEdge(127, 139, MoveType.UBAHN);

        //edges from node 129
        graph.addEdge(128, 134, MoveType.TAXI);
        graph.addEdge(128, 141, MoveType.TAXI);
        graph.addEdge(128, 142, MoveType.TAXI);

        //edges from node 130
        graph.addEdge(129, 130, MoveType.TAXI);
        graph.addEdge(129, 138, MoveType.TAXI);

        //edges from node 132
        graph.addEdge(131, 139, MoveType.TAXI);

        //edges from node 133
        graph.addEdge(132, 140, MoveType.TAXI);
        graph.addEdge(132, 156, MoveType.TAXI);
        graph.addEdge(132, 156, MoveType.BUS);

        //edges from node 134
        graph.addEdge(133, 140, MoveType.TAXI);
        graph.addEdge(133, 141, MoveType.TAXI);

        //edges from node 135
        graph.addEdge(134, 136, MoveType.TAXI);
        graph.addEdge(134, 160, MoveType.TAXI);
        graph.addEdge(134, 160, MoveType.BUS);
        graph.addEdge(134, 143, MoveType.TAXI);

        //edges from node 136
        graph.addEdge(135, 161, MoveType.TAXI);

        //edges from node 137
        graph.addEdge(136, 146, MoveType.TAXI);

        //edges from node 138
        graph.addEdge(137, 149, MoveType.TAXI);
        graph.addEdge(137, 151, MoveType.TAXI);

        //edges from node 139
        graph.addEdge(138, 152, MoveType.TAXI);
        graph.addEdge(138, 153, MoveType.TAXI);
        graph.addEdge(138, 139, MoveType.TAXI);

        //edges from node 140
        graph.addEdge(139, 153, MoveType.TAXI);
        graph.addEdge(139, 153, MoveType.BUS);
        graph.addEdge(139, 152, MoveType.UBAHN);

        //edges from node 141
        graph.addEdge(140, 141, MoveType.TAXI);
        graph.addEdge(140, 157, MoveType.TAXI);

        //edges from node 142
        graph.addEdge(141, 157, MoveType.TAXI);
        graph.addEdge(141, 156, MoveType.BUS);
        graph.addEdge(141, 142, MoveType.TAXI);

        //edges from node 143
        graph.addEdge(142, 159, MoveType.TAXI);

        //edges from node 144
        graph.addEdge(143, 144, MoveType.TAXI);
        graph.addEdge(143, 162, MoveType.TAXI);
        graph.addEdge(143, 162, MoveType.BUS);

        //edges from node 145
        graph.addEdge(144, 145, MoveType.TAXI);

        //edges from node 146
        graph.addEdge(145, 162, MoveType.TAXI);
        graph.addEdge(145, 146, MoveType.TAXI);

        //edges from node 147
        graph.addEdge(146, 163, MoveType.TAXI);

        //edges from node 148
        graph.addEdge(147, 163, MoveType.TAXI);
        graph.addEdge(147, 148, MoveType.TAXI);

        //edges from node 149
        graph.addEdge(148, 150, MoveType.TAXI);
        graph.addEdge(148, 164, MoveType.TAXI);

        //edges from node 150
        graph.addEdge(149, 150, MoveType.TAXI);

        //edges from node 151
        graph.addEdge(150, 151, MoveType.TAXI);
        graph.addEdge(150, 164, MoveType.TAXI);
        graph.addEdge(150, 165, MoveType.TAXI);

        //edges from node 152
        graph.addEdge(151, 152, MoveType.TAXI);

        //edges from node 153
        graph.addEdge(152, 165, MoveType.TAXI);
        graph.addEdge(152, 162, MoveType.UBAHN);
        graph.addEdge(152, 153, MoveType.TAXI);
        graph.addEdge(152, 153, MoveType.BUS);
        graph.addEdge(152, 184, MoveType.UBAHN);
        graph.addEdge(152, 183, MoveType.BUS);
        graph.addEdge(152, 166, MoveType.TAXI);
        graph.addEdge(152, 179, MoveType.BUS);

        //edges from node 154
        graph.addEdge(153, 154, MoveType.TAXI);
        graph.addEdge(153, 155, MoveType.BUS);

        //edges from node 155
        graph.addEdge(154, 167, MoveType.TAXI);
        graph.addEdge(154, 155, MoveType.TAXI);

        //edges from node 156
        graph.addEdge(155, 168, MoveType.TAXI);
        graph.addEdge(155, 156, MoveType.TAXI);
        graph.addEdge(155, 156, MoveType.BUS);
        graph.addEdge(155, 183, MoveType.BUS);

        //edges from node 157
        graph.addEdge(156, 169, MoveType.TAXI);
        graph.addEdge(156, 184, MoveType.BUS);
        graph.addEdge(156, 157, MoveType.TAXI);

        //edges from node 158
        graph.addEdge(157, 158, MoveType.TAXI);

        //edges from node 159
        graph.addEdge(158, 169, MoveType.TAXI);
        graph.addEdge(158, 185, MoveType.TAXI);
        graph.addEdge(158, 197, MoveType.TAXI);
        graph.addEdge(158, 171, MoveType.TAXI);

        //edges from node 160
        graph.addEdge(159, 160, MoveType.TAXI);
        graph.addEdge(159, 172, MoveType.TAXI);

        //edges from node 161
        graph.addEdge(160, 173, MoveType.TAXI);
        graph.addEdge(160, 198, MoveType.BUS);

        //edges from node 162
        graph.addEdge(161, 174, MoveType.TAXI);

        //edges from node 163
        graph.addEdge(162, 176, MoveType.TAXI);
        graph.addEdge(162, 175, MoveType.BUS);
        graph.addEdge(162, 190, MoveType.BUS);

        //edges from node 164
        graph.addEdge(163, 178, MoveType.TAXI);
        graph.addEdge(163, 177, MoveType.TAXI);

        //edges from node 165
        graph.addEdge(164, 178, MoveType.TAXI);
        graph.addEdge(164, 179, MoveType.TAXI);
        graph.addEdge(164, 190, MoveType.BUS);
        graph.addEdge(164, 179, MoveType.BUS);

        //edges from node 166
        graph.addEdge(165, 180, MoveType.TAXI);
        graph.addEdge(165, 179, MoveType.BUS);
        graph.addEdge(165, 182, MoveType.TAXI);

        //edges from node 167
        graph.addEdge(166, 182, MoveType.TAXI);
        graph.addEdge(166, 167, MoveType.TAXI);

        //edges from node 168
        graph.addEdge(167, 183, MoveType.TAXI);

        //edges from node 169
        graph.addEdge(168, 183, MoveType.TAXI);

        //edges from node 170
        graph.addEdge(169, 184, MoveType.TAXI);

        //edges from node 171
        graph.addEdge(170, 198, MoveType.TAXI);
        graph.addEdge(170, 172, MoveType.TAXI);
        graph.addEdge(170, 174, MoveType.TAXI);

        //edges from node 172
        graph.addEdge(171, 186, MoveType.TAXI);

        //edges from node 173
        graph.addEdge(172, 173, MoveType.TAXI);
        graph.addEdge(172, 187, MoveType.TAXI);

        //edges from node 174
        graph.addEdge(173, 174, MoveType.TAXI);

        //edges from node 176
        graph.addEdge(175, 176, MoveType.TAXI);
        graph.addEdge(175, 188, MoveType.TAXI);
        graph.addEdge(175, 189, MoveType.BUS);

        //edges from node 178
        graph.addEdge(177, 190, MoveType.TAXI);
        graph.addEdge(177, 188, MoveType.TAXI);

        //edges from node 179
        graph.addEdge(178, 190, MoveType.TAXI);

        //edges from node 180
        graph.addEdge(179, 180, MoveType.TAXI);
        graph.addEdge(179, 191, MoveType.TAXI);
        graph.addEdge(179, 189, MoveType.BUS);
        graph.addEdge(179, 192, MoveType.TAXI);
        graph.addEdge(179, 183, MoveType.BUS);

        //edges from node 181
        graph.addEdge(180, 192, MoveType.TAXI);
        graph.addEdge(180, 181, MoveType.TAXI);

        //edges from node 182
        graph.addEdge(181, 182, MoveType.TAXI);
        graph.addEdge(181, 192, MoveType.TAXI);

        //edges from node 183
        graph.addEdge(182, 195, MoveType.TAXI);

        //edges from node 184
        graph.addEdge(183, 184, MoveType.TAXI);
        graph.addEdge(183, 184, MoveType.BUS);

        //edges from node 185
        graph.addEdge(184, 185, MoveType.TAXI);
        graph.addEdge(184, 186, MoveType.BUS);

        //edges from node 186
        graph.addEdge(185, 197, MoveType.TAXI);

        //edges from node 187
        graph.addEdge(186, 197, MoveType.TAXI);

        //edges from node 188
        graph.addEdge(187, 198, MoveType.TAXI);

        //edges from node 189
        graph.addEdge(188, 189, MoveType.TAXI);

        //edges from node 190
        graph.addEdge(189, 190, MoveType.TAXI);
        graph.addEdge(189, 190, MoveType.BUS);
        graph.addEdge(189, 191, MoveType.TAXI);

        //edges from node 191
        graph.addEdge(190, 191, MoveType.TAXI);

        //edges from node 192
        graph.addEdge(191, 193, MoveType.TAXI);

        //edges from node 193
        graph.addEdge(192, 193, MoveType.TAXI);

        //edges from node 194
        graph.addEdge(193, 194, MoveType.TAXI);

        //edges from node 195
        graph.addEdge(194, 196, MoveType.TAXI);

        //edges from node 196
        graph.addEdge(195, 196, MoveType.TAXI);

        //edges from node 198
        graph.addEdge(197, 198, MoveType.TAXI);

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(float delta, RenderPipeline pipeline) {
        for (MapNode node : graph.getNodes()) {
            pipeline.getPrimitiveRenderer().drawCircle(node.getPosition(), 27, Color.BLACK, true);
        }
    }
}
