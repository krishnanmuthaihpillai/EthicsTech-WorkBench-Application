package in.ethicstech.workbench.application;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import in.ethicstech.workbench.application.R;

public class MainActivity extends Activity {

	public static boolean a, b, c, d;

	public static boolean e, f, g, h;

	Button battery_relay, charger_relay, green_LED, orange_LED;
	TextView battery_tv_status, charger_tv_status, power_tv_status,
			bottom_textview_A, bottom_textview_B, bottom_textview_C,
			bottom_textview_D, top_textview_A, top_textview_B, top_textview_C,
			top_textview_D;
	Handler handler1=null, handler2=null, handler3=null;
	Runnable runnable1, runnable2, runnable3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		battery_relay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (WorkBench_Service.batt_relay_boolean) {
					WorkBench_Service.batt_relay_boolean = false;
				} else {
					WorkBench_Service.batt_relay_boolean = true;
				}

			}
		});

		charger_relay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (WorkBench_Service.charg_relay_boolean) {
					WorkBench_Service.charg_relay_boolean = false;
				} else {
					WorkBench_Service.charg_relay_boolean = true;
				}

			}
		});

		green_LED.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (handler2 != null) {
					handler2.removeCallbacks(runnable2);
					handler2 = null;
					WorkBench_Service.A_green_led = false;
					WorkBench_Service.B_green_led = false;
					WorkBench_Service.C_green_led = false;
					WorkBench_Service.D_green_led = false;
				} else {
					a = true;
					b = false;
					c = false;
					d = false;
					WorkBench_Service.A_green_led = false;
					WorkBench_Service.B_green_led = false;
					WorkBench_Service.C_green_led = false;
					WorkBench_Service.D_green_led = false;
					check_green();
				}
			}

		});

		orange_LED.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (handler3 != null) {
					handler3.removeCallbacks(runnable3);
					handler3 = null;
					WorkBench_Service.A_orange_led = false;
					WorkBench_Service.B_orange_led = false;
					WorkBench_Service.C_green_led = false;
					WorkBench_Service.D_orange_led = false;
				} else {
					e = true;
					f = false;
					g = false;
					h = false;
					WorkBench_Service.A_orange_led = false;
					WorkBench_Service.B_orange_led = false;
					WorkBench_Service.C_green_led = false;
					WorkBench_Service.D_orange_led = false;
					check_orange();
				}
			}

		});
	}

	private void init() {

		top_textview_A = (TextView) findViewById(R.id.top_A);
		top_textview_B = (TextView) findViewById(R.id.top_B);
		top_textview_C = (TextView) findViewById(R.id.top_C);
		top_textview_D = (TextView) findViewById(R.id.top_D);

		bottom_textview_A = (TextView) findViewById(R.id.bottom_A);
		bottom_textview_B = (TextView) findViewById(R.id.bottom_B);
		bottom_textview_C = (TextView) findViewById(R.id.bottom_C);
		bottom_textview_D = (TextView) findViewById(R.id.bottom_D);

		green_LED = (Button) findViewById(R.id.green_button);
		orange_LED = (Button) findViewById(R.id.orange_button);
		battery_relay = (Button) findViewById(R.id.bat_button);
		battery_tv_status = (TextView) findViewById(R.id.battery_tv_status);
		charger_relay = (Button) findViewById(R.id.charg_button);
		charger_tv_status = (TextView) findViewById(R.id.charger_tv_status);
		power_tv_status = (TextView) findViewById(R.id.power_tv_status);
		startService(new Intent(getApplicationContext(),
				WorkBench_Service.class));

		ui_interface();

	}

	private void ui_interface() {
		runnable1 = new Runnable() {
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {

						if (WorkBench_Service.batt_relay_boolean) {
							battery_tv_status.setText("ON");
						} else {
							battery_tv_status.setText("OFF");
						}
						if (WorkBench_Service.charg_relay_boolean) {
							charger_tv_status.setText("ON");
						} else {
							charger_tv_status.setText("OFF");
						}

						if (WorkBench_Service.main_power_boolean) {
							power_tv_status.setText("ON");
						} else {
							power_tv_status.setText("OFF");
						}

						if (WorkBench_Service.A_BottomCheck) {

							set_textview_green(bottom_textview_A);
							set_textview_normal(bottom_textview_B);
							set_textview_normal(bottom_textview_C);
							set_textview_normal(bottom_textview_D);
							
							set_textview_normal(top_textview_A);
							set_textview_normal(top_textview_B);
							set_textview_normal(top_textview_C);
							set_textview_normal(top_textview_D);
							
						} else if (WorkBench_Service.B_BottomCheck) {

							set_textview_green(bottom_textview_B);
							set_textview_normal(bottom_textview_A);
							set_textview_normal(bottom_textview_C);
							set_textview_normal(bottom_textview_D);
							
							set_textview_normal(top_textview_A);
							set_textview_normal(top_textview_B);
							set_textview_normal(top_textview_C);
							set_textview_normal(top_textview_D);
							
						} else if (WorkBench_Service.C_BottomCheck) {

							set_textview_green(bottom_textview_C);
							set_textview_normal(bottom_textview_A);
							set_textview_normal(bottom_textview_B);
							set_textview_normal(bottom_textview_D);
							
							set_textview_normal(top_textview_A);
							set_textview_normal(top_textview_B);
							set_textview_normal(top_textview_C);
							set_textview_normal(top_textview_D);
							
						} else if (WorkBench_Service.D_BottomCheck) {

							set_textview_green(bottom_textview_D);
							set_textview_normal(bottom_textview_A);
							set_textview_normal(bottom_textview_B);
							set_textview_normal(bottom_textview_C);
							
							set_textview_normal(top_textview_A);
							set_textview_normal(top_textview_B);
							set_textview_normal(top_textview_C);
							set_textview_normal(top_textview_D);
						}

						if (WorkBench_Service.A_TopCheck) {

							set_textview_orange(top_textview_A);
							set_textview_normal(top_textview_B);
							set_textview_normal(top_textview_C);
							set_textview_normal(top_textview_D);
							
							set_textview_normal(bottom_textview_A);
							set_textview_normal(bottom_textview_B);
							set_textview_normal(bottom_textview_C);
							set_textview_normal(bottom_textview_D);
							
						} else if (WorkBench_Service.B_TopCheck) {

							set_textview_orange(top_textview_B);
							set_textview_normal(top_textview_A);
							set_textview_normal(top_textview_C);
							set_textview_normal(top_textview_D);
							
							set_textview_normal(bottom_textview_A);
							set_textview_normal(bottom_textview_B);
							set_textview_normal(bottom_textview_C);
							set_textview_normal(bottom_textview_D);
							
						} else if (WorkBench_Service.C_TopCheck) {

							set_textview_orange(top_textview_C);
							set_textview_normal(top_textview_A);
							set_textview_normal(top_textview_B);
							set_textview_normal(top_textview_D);
							
							set_textview_normal(bottom_textview_A);
							set_textview_normal(bottom_textview_B);
							set_textview_normal(bottom_textview_C);
							set_textview_normal(bottom_textview_D);
							
						} else if (WorkBench_Service.D_TopCheck) {

							set_textview_orange(top_textview_D);
							set_textview_normal(top_textview_A);
							set_textview_normal(top_textview_B);
							set_textview_normal(top_textview_C);
							
							set_textview_normal(bottom_textview_A);
							set_textview_normal(bottom_textview_B);
							set_textview_normal(bottom_textview_C);
							set_textview_normal(bottom_textview_D);
						}

					}

				});

				handler1.postDelayed(runnable1, 100);

			}
		};
		handler1 = new Handler();
		handler1.postDelayed(runnable1, 100);

	}



	private void set_textview_orange(final TextView view) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {

				view.setBackgroundColor(Color.parseColor("#FF6600"));

			}

		});
	}

	private void set_textview_green(final TextView view) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {

				view.setBackgroundColor(Color.parseColor("#00ff00"));

			}

		});
	}

	private void set_textview_normal(final TextView view) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {

				view.setBackgroundColor(Color.parseColor("#FFFFFF"));

			}

		});
	}

	private void check_green() {
		runnable2 = new Runnable() {
			public void run() {

				if (a) {
					a = false;
					b = true;
					c = false;
					d = false;
					check_green(1);
				} else if (b) {
					a = false;
					b = false;
					c = true;
					d = false;
					check_green(2);
				} else if (c) {
					a = false;
					b = false;
					c = false;
					d = true;
					check_green(3);
				} else if (d) {
					a = true;
					b = false;
					c = false;
					d = false;
					check_green(4);
				}

				handler2.postDelayed(runnable2, 1000);

			}

		};
		handler2 = new Handler();
		handler2.postDelayed(runnable2, 500);
	}

	private void check_green(int i) {
		if (i == 1) {
			WorkBench_Service.A_green_led = true;
			WorkBench_Service.B_green_led = false;
			WorkBench_Service.C_green_led = false;
			WorkBench_Service.D_green_led = false;
		}
		if (i == 2) {
			WorkBench_Service.A_green_led = false;
			WorkBench_Service.B_green_led = true;
			WorkBench_Service.C_green_led = false;
			WorkBench_Service.D_green_led = false;
		}

		if (i == 3) {
			WorkBench_Service.A_green_led = false;
			WorkBench_Service.B_green_led = false;
			WorkBench_Service.C_green_led = true;
			WorkBench_Service.D_green_led = false;
		}
		if (i == 4) {
			WorkBench_Service.A_green_led = false;
			WorkBench_Service.B_green_led = false;
			WorkBench_Service.C_green_led = false;
			WorkBench_Service.D_green_led = true;
		}
	}

	private void check_orange() {
		runnable3 = new Runnable() {
			public void run() {

				if (e) {
					e = false;
					f = true;
					g = false;
					h = false;
					check_orange(1);
				} else if (f) {
					e = false;
					f = false;
					g = true;
					h = false;
					check_orange(2);
				} else if (g) {
					e = false;
					f = false;
					g = false;
					h = true;
					check_orange(3);
				} else if (h) {
					e = true;
					f = false;
					g = false;
					h = false;
					check_orange(4);
				}

				handler3.postDelayed(runnable3, 1000);

			}

		};
		handler3 = new Handler();
		handler3.postDelayed(runnable3, 500);

	}

	private void check_orange(int i) {

		if (i == 1) {
			WorkBench_Service.A_orange_led = true;
			WorkBench_Service.B_orange_led = false;
			WorkBench_Service.C_orange_led = false;
			WorkBench_Service.D_orange_led = false;
		}
		if (i == 2) {
			WorkBench_Service.A_green_led = false;
			WorkBench_Service.B_orange_led = true;
			WorkBench_Service.C_orange_led = false;
			WorkBench_Service.D_orange_led = false;
		}

		if (i == 3) {
			WorkBench_Service.A_orange_led = false;
			WorkBench_Service.B_orange_led = false;
			WorkBench_Service.C_orange_led = true;
			WorkBench_Service.D_orange_led = false;
		}
		if (i == 4) {
			WorkBench_Service.A_green_led = false;
			WorkBench_Service.B_orange_led = false;
			WorkBench_Service.C_orange_led = false;
			WorkBench_Service.D_orange_led = true;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		   if(handler1 != null) 
		    {   handler1.removeCallbacks(runnable1);
//			   handler1.removeCallbacksAndMessages(null); 
		       handler1 = null; 
		    } 
		   
		   if(handler1 != null) 
		    {   handler2.removeCallbacks(runnable2);
//			   handler1.removeCallbacksAndMessages(null); 
		    handler2 = null; 
		    } 
		   if(handler3 != null) 
		    {   handler3.removeCallbacks(runnable3);
//			   handler1.removeCallbacksAndMessages(null); 
		    handler3 = null; 
		    } 
//		handler1.removeCallbacks(runnable1);
//		handler2.removeCallbacks(runnable2);
//		handler3.removeCallbacks(runnable3);
		   
		   

		 
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		  open();
	}
	 public void open(){
	      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	      alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");
	      
	      alertDialogBuilder.setPositiveButton("Run in Background", new DialogInterface.OnClickListener() {
	         @Override
	         public void onClick(DialogInterface arg0, int arg1) {
//	            Toast.makeText(MainActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
	        	  finish();
	         }
	      });
	      
	      alertDialogBuilder.setNegativeButton("Quit Now",new DialogInterface.OnClickListener() {
	         @Override
	         public void onClick(DialogInterface dialog, int which) {
	    	   Intent intent = new Intent(getApplicationContext(), WorkBench_Service.class);
	           stopService(intent);
	            finish();
	         }
	      });
	      
	      AlertDialog alertDialog = alertDialogBuilder.create();
	      alertDialog.show();
	   }
	
	
}
