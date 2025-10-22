public class Microbenchmark {
    public static void main(String[] args) { /* Class to test the Microbenchmark*/
        System.out.println("MicroBenchmark");
        /* looping through the numbers so we can get every number from 100k to 1M
        incrementing by 100K
         */
        for (int N = 100_000; N <= 1_000_000; N += 100_000) {
            CSArrayList<Integer> a = new CSArrayList<>();
            long t0 = System.nanoTime();
            for (int i = 0; i < N; i++) {
                a.add(i);
            }
            long t1 = System.nanoTime();
            java.util.Random r = new java.util.Random(0);
            long s = 0;
            for (int i = 0; i < N; i++) {
                s += a.get(r.nextInt(N));
                long t2 = System.nanoTime();
                System.out.printf("N=%d append=%.1f ms get=%.1f ms\n", N , (t1 - t0) / 1e6, (t2 - t1) / 1e6);
            }
        }
        /* What I notice about the results is that as the numbers for the N starts to increase so does the append
        but what I also notice is that the numbers for the get times when they reach a certain threshold
        which is around the ten thousands it sort of resets back down to 1000 then the milliseconds start to increase again
        so it is sort of looping kind of how I imagine it.
         */
    }
}
