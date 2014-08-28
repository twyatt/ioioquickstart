package ioioquickstart;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;

/**
 * Controls the on-board IOIO stat LED.
 * 
 * https://github.com/ytai/ioio/wiki/Digital-IO
 */
public class StatLED {
	
	private boolean isOn;
	private DigitalOutput led;
	
	public void setOn(boolean isOn) {
		this.isOn = isOn;
	}
	
	public void on() {
		isOn = true;
	}
	
	public void off() {
		isOn = false;
	}
	
	public void toggle() {
		isOn = !isOn;
	}
	
	public void setup(IOIO ioio) throws ConnectionLostException {
		led = ioio.openDigitalOutput(IOIO.LED_PIN, isOn);
	}

	public void loop() throws ConnectionLostException {
		led.write(!isOn);
	}


}
