public class ExerciseRunner {

    public static void main(String[] args)  {
        TV tv = new TV();
        CompleteRemote remoteTV = new CompleteRemote(tv);

        Radio radio = new Radio();
        Remote remoteRadio = new Remote(radio);

        remoteTV.chooseChannel(37);
        remoteTV.togglePower();
        remoteTV.volumeUp();
        remoteTV.volumeUp();

        remoteRadio.channelUp();
        remoteRadio.volumeUp();

        System.out.println(tv);
        System.out.println(radio);
    }
}