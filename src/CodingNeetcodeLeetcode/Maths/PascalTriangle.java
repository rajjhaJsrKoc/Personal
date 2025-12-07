package CodingNeetcodeLeetcode.Maths;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    public static void main(String[] args){
        int generatePascalrows = 5;
        List<List<Integer>> pascalTriangle = generatePascal(generatePascalrows);
        pascalTriangle.stream().flatMap(e->e.stream()).forEach(System.out::print);
    }

    private static List<List<Integer>> generatePascal(int n) {
        List<List<Integer>> pascal = new ArrayList<>();
        for (int i = 1;i<=n;i++){
            pascal.add(nCrFormula(i));
        }
        return pascal;
    }
    public static List<Integer> nCrFormula(int i){
        List<Integer> rowResult = new ArrayList<>();
        rowResult.add(1);
        int res = 1;
        for (int j = 1;j<i;j++){
            res = res*(i-j);
            res = res/j;
            rowResult.add(res);
        }
        return rowResult;
    }
}
