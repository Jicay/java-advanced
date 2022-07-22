public class Triplet<K,L,M> {
    private K first;
    private L second;
    private M third;


    public Triplet(K first, L second, M third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }


    public K getFirst() {
        return first;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public L getSecond() {
        return second;
    }

    public void setSecond(L second) {
        this.second = second;
    }

    public M getThird() {
        return third;
    }

    public void setThird(M third) {
        this.third = third;
    }

    @Override
    public String toString() {
        return "Triplet{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}
