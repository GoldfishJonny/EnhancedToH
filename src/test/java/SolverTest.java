import com.team4.Solver;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SolverTest {

    @Test
    public void moveDisks(){
        int[] diskPosition = {2, 1, 0};
        int disksToMove = 3;
        int targetPeg = 2;
        List<int[]> answer = new ArrayList<>();
        Solver.moveDisks(diskPosition, disksToMove, targetPeg, answer);
        List<int[]> answer1 = new ArrayList<>();
        answer1.add(new int[]{1, 1, 0});
        answer1.add(new int[]{1, 1, 2});
        answer1.add(new int[]{0, 1, 2});
        answer1.add(new int[]{0, 2, 2});
        answer1.add(new int[]{2, 2, 2});
        Assert.assertArrayEquals(answer1.get(0), answer.get(0));
        Assert.assertArrayEquals(answer1.get(1), answer.get(1));
        Assert.assertArrayEquals(answer1.get(2), answer.get(2));
        System.out.println("-----------------------------------");
        int[] diskPos2 = {0, 0, 0};
        List<int[]> answer3 = new ArrayList<>();
        Solver.moveDisks(diskPos2, disksToMove, targetPeg, answer3);
        List<int[]> answer4 = new ArrayList<>();
        answer4.add(new int[]{2, 0, 0});
        answer4.add(new int[]{2, 1, 0});
        answer4.add(new int[]{1, 1, 0});
        answer4.add(new int[]{1, 1, 2});
        answer4.add(new int[]{0, 1, 2});
        answer4.add(new int[]{0, 2, 2});
        answer4.add(new int[]{2, 2, 2});
        Assert.assertArrayEquals(answer4.get(0), answer3.get(0));
        Assert.assertArrayEquals(answer4.get(1), answer3.get(1));
        Assert.assertArrayEquals(answer4.get(2), answer3.get(2));
        System.out.println("-----------------------------------");
        int diskPos3[] = {1, 0, 0};
        List<int[]> answer5 = new ArrayList<>();
        Solver.moveDisks(diskPos3, disksToMove, targetPeg, answer5);
        List<int[]> answer6 = new ArrayList<>();
        answer6.add(new int[]{2, 0, 0});
        answer6.add(new int[]{2, 1, 0});
        answer6.add(new int[]{1, 1, 0});
        answer6.add(new int[]{1, 1, 2});
        answer6.add(new int[]{0, 1, 2});
        answer6.add(new int[]{0, 2, 2});
        answer6.add(new int[]{2, 2, 2});
        Assert.assertArrayEquals(answer6.get(0), answer5.get(0));
        Assert.assertArrayEquals(answer6.get(1), answer5.get(1));
        Assert.assertArrayEquals(answer6.get(2), answer5.get(2));
        System.out.println("-----------------------------------");
        int diskPos4[] = {0, 1, 0};
        List<int[]> answer7 = new ArrayList<>();
        Solver.moveDisks(diskPos4, disksToMove, targetPeg, answer7);
        List<int[]> answer8 = new ArrayList<>();
        answer8.add(new int[]{1, 1, 0});
        answer8.add(new int[]{1, 1, 2});
        answer8.add(new int[]{0, 1, 2});
        answer8.add(new int[]{0, 2, 2});
        answer8.add(new int[]{2, 2, 2});
        Assert.assertArrayEquals(answer8.get(0), answer7.get(0));
        Assert.assertArrayEquals(answer8.get(1), answer7.get(1));
        Assert.assertArrayEquals(answer8.get(2), answer7.get(2));
    }
}
