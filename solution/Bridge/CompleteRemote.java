public class CompleteRemote extends Remote {

    public CompleteRemote(Device device) {
        super(device);
    }

    public void mute() {
        device.setVolume(0);
    }

    public void chooseChannel(int channel) {
        device.setChannel(channel);
    }
}
