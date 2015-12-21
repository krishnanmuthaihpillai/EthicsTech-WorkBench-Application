package in.ethicstech.workbench.application;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import ioio.lib.api.AnalogInput;
import ioio.lib.api.DigitalInput;
import ioio.lib.api.DigitalOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOService;

public class WorkBench_Service extends IOIOService {

	public static boolean A_BottomCheck;
	public static boolean B_BottomCheck;
	public static boolean C_BottomCheck;
	public static boolean D_BottomCheck;

	
	public static boolean A_TopCheck;
	public static boolean B_TopCheck;
	public static boolean C_TopCheck;
	public static boolean D_TopCheck;
	
	
	public static boolean A_green_led;
	public static boolean B_green_led;
	public static boolean C_green_led;
	public static boolean D_green_led;

	public static boolean A_orange_led;
	public static boolean B_orange_led;
	public static boolean C_orange_led;
	public static boolean D_orange_led;

	public static boolean batt_relay_boolean;
	public static boolean charg_relay_boolean;
	public static boolean main_power_boolean;

	@Override
	protected IOIOLooper createIOIOLooper() {
		return new BaseIOIOLooper() {
			// output
			private DigitalOutput batt_relay;
			private DigitalOutput charg_relay;

			private DigitalOutput A_GREEN_LED, B_GREEN_LED, C_GREEN_LED,
					D_GREEN_LED;

			private DigitalOutput A_ORANGE_LED, B_ORANGE_LED, C_ORANGE_LED,
					D_ORANGE_LED;

			// Input

			
			private DigitalInput A_Bottom, B_Bottom, C_Bottom,D_Bottom;
			
			
			private DigitalInput A_Top, B_Top, C_Top,D_Top;
			
			// AnalogInput in ;

			private DigitalInput main_power;

			@Override
			protected void setup() throws ConnectionLostException,
					InterruptedException {

				
				A_Bottom = ioio_.openDigitalInput(35);
				B_Bottom = ioio_.openDigitalInput(34);
				C_Bottom = ioio_.openDigitalInput(33);
				D_Bottom = ioio_.openDigitalInput(32);
				
				
				A_Top = ioio_.openDigitalInput(31);
				B_Top = ioio_.openDigitalInput(30);
				C_Top = ioio_.openDigitalInput(29);
				D_Top = ioio_.openDigitalInput(28);
				
				
				batt_relay = ioio_.openDigitalOutput(46, false);
				charg_relay = ioio_.openDigitalOutput(45, false);

				main_power = ioio_.openDigitalInput(44);

				A_GREEN_LED = ioio_.openDigitalOutput(5, false);
				B_GREEN_LED = ioio_.openDigitalOutput(6, false);
				C_GREEN_LED = ioio_.openDigitalOutput(7, false);
				D_GREEN_LED = ioio_.openDigitalOutput(8, false);
				// ////////////////////////////////////////////
				A_ORANGE_LED = ioio_.openDigitalOutput(1, false);
				B_ORANGE_LED = ioio_.openDigitalOutput(2, false);
				C_ORANGE_LED = ioio_.openDigitalOutput(3, false);
				D_ORANGE_LED = ioio_.openDigitalOutput(4, false);

				// in = ioio_.openAnalogInput(41);

				A_BottomCheck = false;
				B_BottomCheck = false;
				C_BottomCheck = false;
				D_BottomCheck = false;

				A_TopCheck = false;
				B_TopCheck = false;
				C_TopCheck = false;
				D_TopCheck = false;
				
				
				
				A_green_led = false;
				B_green_led = false;
				C_green_led = false;
				D_green_led = false;

				A_orange_led = false;
				B_orange_led = false;
				C_orange_led = false;
				D_orange_led = false;

				batt_relay_boolean = false;
				charg_relay_boolean = false;
				main_power_boolean = false;
			}

			@Override
			public void loop() throws ConnectionLostException,
					InterruptedException {

				if (!A_Bottom.read()) {
					//A Bottom
					Log.i("WorkBench_Service","Sucess");
					A_BottomCheck = true;
					B_BottomCheck = false;
					C_BottomCheck = false;
					D_BottomCheck = false;
					
					A_TopCheck = false;
					B_TopCheck = false;
					C_TopCheck = false;
					D_TopCheck = false;
					
					
				}
				
				else if (!B_Bottom.read()) {
//					Log.i("WorkBench_Service","fail");
					Log.i("WorkBench_Service","B Bottom");
					A_BottomCheck = false;
					B_BottomCheck = true;
					C_BottomCheck = false;
					D_BottomCheck = false;
					
					A_TopCheck = false;
					B_TopCheck = false;
					C_TopCheck = false;
					D_TopCheck = false;
				}
				else if (!C_Bottom.read()) {
					//C Bottom
					Log.i("WorkBench_Service","C Bottom");
					A_BottomCheck = false;
					B_BottomCheck = false;
					C_BottomCheck = true;
					D_BottomCheck = false;
					
					A_TopCheck = false;
					B_TopCheck = false;
					C_TopCheck = false;
					D_TopCheck = false;
				} 
				
				else if (!D_Bottom.read()) {
					//D Bottom	
                	   Log.i("WorkBench_Service","D Bottom");
					A_BottomCheck = false;
					B_BottomCheck = false;
					C_BottomCheck = false;
					D_BottomCheck = true;
					
					A_TopCheck = false;
					B_TopCheck = false;
					C_TopCheck = false;
					D_TopCheck = false;
				}
				if (A_Top.read()) {
					//A Top
					Log.i("WorkBench_Service","Sucess");
					A_TopCheck = true;
					B_TopCheck = false;
					C_TopCheck = false;
					D_TopCheck = false;
					
					A_BottomCheck = false;
					B_BottomCheck = false;
					C_BottomCheck = false;
					D_BottomCheck = false;
					
				}else if (B_Top.read()) {
//					Log.i("WorkBench_Service","fail");
					Log.i("WorkBench_Service","B Top");
					A_TopCheck = false;
					B_TopCheck = true;
					C_TopCheck = false;
					D_TopCheck = false;
					
					A_BottomCheck = false;
					B_BottomCheck = false;
					C_BottomCheck = false;
					D_BottomCheck = false;
				}else if (C_Top.read()) {
					//C Top
					Log.i("WorkBench_Service","C Top");
					A_TopCheck = false;
					B_TopCheck = false;
					C_TopCheck = true;
					D_TopCheck = false;
					
					A_BottomCheck = false;
					B_BottomCheck = false;
					C_BottomCheck = false;
					D_BottomCheck = false;
				} else if (D_Top.read()) {
					//D Top	
                	   Log.i("WorkBench_Service","D Top");
					A_TopCheck = false;
					B_TopCheck = false;
					C_TopCheck = false;
					D_TopCheck = true;
					
					A_BottomCheck = false;
					B_BottomCheck = false;
					C_BottomCheck = false;
					D_BottomCheck = false;
				}
				//
				// float volts = in.getVoltageBuffered();

				// float volts = in.getVoltage();
				// Log.i("volts", ""+volts);
				batt_relay.write(batt_relay_boolean);
				charg_relay.write(charg_relay_boolean);

				if (main_power.read()) {
					main_power_boolean = true;
				} else {
					main_power_boolean = false;
				}

				A_GREEN_LED.write(A_green_led);
				B_GREEN_LED.write(B_green_led);
				C_GREEN_LED.write(C_green_led);
				D_GREEN_LED.write(D_green_led);

				A_ORANGE_LED.write(A_orange_led);
				B_ORANGE_LED.write(B_orange_led);
				C_ORANGE_LED.write(C_orange_led);
				D_ORANGE_LED.write(D_orange_led);

			}
		};
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
