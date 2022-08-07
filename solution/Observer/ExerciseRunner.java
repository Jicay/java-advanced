public class ExerciseRunner {

    public static void main(String[] args)  {
        ValuePublisher publisher = new ValuePublisher();

        BinaryBaseObserver binaryObserver = new BinaryBaseObserver();
        DecimalBaseObserver decimalObserver = new DecimalBaseObserver();
        HexaBaseObserver hexaObserver = new HexaBaseObserver();

        publisher.subscribe(binaryObserver);
        publisher.subscribe(decimalObserver);
        publisher.subscribe(hexaObserver);

        publisher.updateState(1254);
        publisher.updateState(45839);
        publisher.updateState(5984382);

        System.out.println("Binary " + binaryObserver.getEvents());
        System.out.println("Decimal " + decimalObserver.getEvents());
        System.out.println("Hexa " + hexaObserver.getEvents());
    }
}