package ioioquickstart.console;

import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOConnectionManager.Thread;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.pc.IOIOConsoleApp;
import ioioquickstart.StatLED;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelloIOIOConsole extends IOIOConsoleApp {
	
	private final StatLED led = new StatLED();

	// Boilerplate main(). Copy-paste this code into any IOIOapplication.
	public static void main(String[] args) throws Exception {
		new HelloIOIOConsole().go(args);
	}

	@Override
	protected void run(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		boolean abort = false;
		String line;
		while (!abort && (line = reader.readLine()) != null) {
			if (line.equals("t")) {
				led.toggle();
			} else if (line.equals("n")) {
				led.on();
			} else if (line.equals("f")) {
				led.off();
			} else if (line.equals("q")) {
				abort = true;
			} else {
				System.out
						.println("Unknown input. t=toggle, n=on, f=off, q=quit.");
			}
		}
	}

	@Override
	public IOIOLooper createIOIOLooper(String connectionType, Object extra) {
		return new BaseIOIOLooper() {

			@Override
			protected void setup() throws ConnectionLostException,
					InterruptedException {
				led.setup(ioio_);
			}

			@Override
			public void loop() throws ConnectionLostException,
					InterruptedException {
				led.loop();
				Thread.sleep(10);
			}
		};
	}
}
