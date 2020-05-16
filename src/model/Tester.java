package model;

public class Tester {
    public static void main(String[] args) {
        GestorePopolazione p = new GestorePopolazione(100,10,3000);
        System.out.println(p.array_popolazione.size());
        for(int i=0;i<p.array_popolazione.size();i++){
            System.out.print(p.array_popolazione.get(i).eta + " ");
            System.out.print(p.array_popolazione.get(i).individuo);
            System.out.println();
        }
    }
}
