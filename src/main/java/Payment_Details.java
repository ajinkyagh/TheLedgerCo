public class Payment_Details {
    private final float no;
    private final float Lumpsum;

    public Payment_Details(float totalAmountWithLuumpsum, float no) {
        this.Lumpsum=totalAmountWithLuumpsum;
        this.no=no;
    }

    public float getNo() {
        return no;
    }

    public float getLumpsum() {
        return Lumpsum;
    }
}
