import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class BridgeTest {

    @Test
    void checkClass() {
        var deviceClass = Device.class;
        assertThat(deviceClass)
                .withFailMessage("Device should be an interface")
                .isInterface();
        var tvClass = TV.class;
        assertThat(tvClass.getInterfaces())
                .withFailMessage("TV should implement Device interface")
                .containsExactly(Device.class);
        var radioClass = Radio.class;
        assertThat(radioClass.getInterfaces())
                .withFailMessage("Radio should implement Device interface")
                .containsExactly(Device.class);

        var completeRemoteClass = CompleteRemote.class;
        assertThat(completeRemoteClass.getSuperclass())
                .withFailMessage("CompleteRemote should inherit Remote")
                .isEqualTo(Remote.class);
        var remoteClass = Remote.class;
        try {
            var deviceField = remoteClass.getDeclaredField("device");
            assertThat(deviceField.getType())
                    .withFailMessage("device attribute should be of Device class")
                    .isEqualTo(Device.class);
        } catch (NoSuchFieldException e) {
            fail("Remote class have a device field");
        }
    }

    @Test
    void tv() {
        TV tv = new TV();
        CompleteRemote completeRemote = new CompleteRemote(tv);

        assertThat(tv.isEnabled())
                .withFailMessage("TV should be disabled")
                .isEqualTo(false);
        assertThat(tv.getVolume())
                .withFailMessage("TV volume should be 0, but was %s", tv.getVolume())
                .isEqualTo(0);
        assertThat(tv.getChannel())
                .withFailMessage("TV channel should be 0, but was %s", tv.getChannel())
                .isEqualTo(0);

        completeRemote.togglePower();
        assertThat(tv.isEnabled())
                .withFailMessage("TV should be enabled")
                .isEqualTo(true);
        assertThat(tv.getVolume())
                .withFailMessage("TV volume should be 0, but was %s", tv.getVolume())
                .isEqualTo(0);
        assertThat(tv.getChannel())
                .withFailMessage("TV channel should be 0, but was %s", tv.getChannel())
                .isEqualTo(0);

        completeRemote.volumeUp();
        assertThat(tv.isEnabled())
                .withFailMessage("TV should be enabled")
                .isEqualTo(true);
        assertThat(tv.getVolume())
                .withFailMessage("TV volume should be 10, but was %s", tv.getVolume())
                .isEqualTo(10);
        assertThat(tv.getChannel())
                .withFailMessage("TV channel should be 0, but was %s", tv.getChannel())
                .isEqualTo(0);

        completeRemote.volumeDown();
        assertThat(tv.isEnabled())
                .withFailMessage("TV should be enabled")
                .isEqualTo(true);
        assertThat(tv.getVolume())
                .withFailMessage("TV volume should be 0, but was %s", tv.getVolume())
                .isEqualTo(0);
        assertThat(tv.getChannel())
                .withFailMessage("TV channel should be 0, but was %s", tv.getChannel())
                .isEqualTo(0);

        completeRemote.channelUp();
        assertThat(tv.isEnabled())
                .withFailMessage("TV should be enabled")
                .isEqualTo(true);
        assertThat(tv.getVolume())
                .withFailMessage("TV volume should be 0, but was %s", tv.getVolume())
                .isEqualTo(0);
        assertThat(tv.getChannel())
                .withFailMessage("TV channel should be 1, but was %s", tv.getChannel())
                .isEqualTo(1);

        completeRemote.channelDown();
        assertThat(tv.isEnabled())
                .withFailMessage("TV should be enabled")
                .isEqualTo(true);
        assertThat(tv.getVolume())
                .withFailMessage("TV volume should be 0, but was %s", tv.getVolume())
                .isEqualTo(0);
        assertThat(tv.getChannel())
                .withFailMessage("TV channel should be 0, but was %s", tv.getChannel())
                .isEqualTo(0);

        completeRemote.togglePower();
        assertThat(tv.isEnabled())
                .withFailMessage("TV should be disabled")
                .isEqualTo(false);
        assertThat(tv.getVolume())
                .withFailMessage("TV volume should be 0, but was %s", tv.getVolume())
                .isEqualTo(0);
        assertThat(tv.getChannel())
                .withFailMessage("TV channel should be 0, but was %s", tv.getChannel())
                .isEqualTo(0);

        completeRemote.channelUp();
        completeRemote.channelUp();
        completeRemote.channelUp();
        completeRemote.channelUp();
        completeRemote.channelUp();
        completeRemote.mute();
        completeRemote.chooseChannel(32);
        assertThat(tv.isEnabled())
                .withFailMessage("TV should be disabled")
                .isEqualTo(false);
        assertThat(tv.getVolume())
                .withFailMessage("TV volume should be 0, but was %s", tv.getVolume())
                .isEqualTo(0);
        assertThat(tv.getChannel())
                .withFailMessage("TV channel should be 32, but was %s", tv.getChannel())
                .isEqualTo(32);
    }

    @Test
    void radio() {
        Radio radio = new Radio();
        Remote remote = new Remote(radio);

        assertThat(radio.isEnabled())
                .withFailMessage("Radio should be disabled")
                .isEqualTo(false);
        assertThat(radio.getVolume())
                .withFailMessage("Radio volume should be 0, but was %s", radio.getVolume())
                .isEqualTo(0);
        assertThat(radio.getChannel())
                .withFailMessage("Radio channel should be 0, but was %s", radio.getChannel())
                .isEqualTo(0);

        remote.togglePower();
        assertThat(radio.isEnabled())
                .withFailMessage("Radio should be enabled")
                .isEqualTo(true);
        assertThat(radio.getVolume())
                .withFailMessage("Radio volume should be 0, but was %s", radio.getVolume())
                .isEqualTo(0);
        assertThat(radio.getChannel())
                .withFailMessage("Radio channel should be 0, but was %s", radio.getChannel())
                .isEqualTo(0);

        remote.volumeUp();
        assertThat(radio.isEnabled())
                .withFailMessage("Radio should be enabled")
                .isEqualTo(true);
        assertThat(radio.getVolume())
                .withFailMessage("Radio volume should be 10, but was %s", radio.getVolume())
                .isEqualTo(10);
        assertThat(radio.getChannel())
                .withFailMessage("Radio channel should be 0, but was %s", radio.getChannel())
                .isEqualTo(0);

        remote.volumeDown();
        assertThat(radio.isEnabled())
                .withFailMessage("Radio should be enabled")
                .isEqualTo(true);
        assertThat(radio.getVolume())
                .withFailMessage("Radio volume should be 0, but was %s", radio.getVolume())
                .isEqualTo(0);
        assertThat(radio.getChannel())
                .withFailMessage("Radio channel should be 0, but was %s", radio.getChannel())
                .isEqualTo(0);

        remote.channelUp();
        assertThat(radio.isEnabled())
                .withFailMessage("Radio should be enabled")
                .isEqualTo(true);
        assertThat(radio.getVolume())
                .withFailMessage("Radio volume should be 0, but was %s", radio.getVolume())
                .isEqualTo(0);
        assertThat(radio.getChannel())
                .withFailMessage("Radio channel should be 1, but was %s", radio.getChannel())
                .isEqualTo(1);

        remote.channelDown();
        assertThat(radio.isEnabled())
                .withFailMessage("Radio should be enabled")
                .isEqualTo(true);
        assertThat(radio.getVolume())
                .withFailMessage("Radio volume should be 0, but was %s", radio.getVolume())
                .isEqualTo(0);
        assertThat(radio.getChannel())
                .withFailMessage("Radio channel should be 0, but was %s", radio.getChannel())
                .isEqualTo(0);

        remote.togglePower();
        assertThat(radio.isEnabled())
                .withFailMessage("Radio should be disabled")
                .isEqualTo(false);
        assertThat(radio.getVolume())
                .withFailMessage("Radio volume should be 0, but was %s", radio.getVolume())
                .isEqualTo(0);
        assertThat(radio.getChannel())
                .withFailMessage("Radio channel should be 0, but was %s", radio.getChannel())
                .isEqualTo(0);
    }
}