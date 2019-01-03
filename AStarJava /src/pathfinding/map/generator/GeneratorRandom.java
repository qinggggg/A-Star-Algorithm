//package pathfinding.map.generator;
//
//import java.util.Random;
//
//import pathfinding.map.WeightedPoint;
//
///**
// * Generate a 2D Boolean map with random point obstacles
// */
//public class GeneratorRandom extends MapGenerator
//{
//    @Override
//    public void addObstacles(Random rnd, boolean[][] map, WeightedPoint start, WeightedPoint goal)
//    {
////        final int pointCount = Math.max(1, (map.length * map[0].length) / (2 + rnd.nextInt(4)));
//    	double percent = 0.40;
//    	int pointCount = (int) (map.length * map[0].length * percent);
//        System.out.println(pointCount);
//        int r, c;
//        for (int i = 0; i < pointCount; i++)
//        {
//            r = rnd.nextInt(Math.max(1, map.length));
//            c = rnd.nextInt(Math.max(1, map[0].length));
//            if ((start.getCol() != c || start.getRow() != r) && (goal.getCol() != c || goal.getRow() != r || map[r][c] == false))
//            {
//                map[r][c] = true;
//            }else {
//            	i--;
//            }
//            
//        }
//    }
//
//    @Override
//    public String getLabel()
//    {
//        return "Random Points";
//    }
//
//}
