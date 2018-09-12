package com.XLWCamrea;

public class MainTest {

	public static void main(String[] args) {
		System.out.println("开始！！！！！！！！！！！！！！！！！！！！！！！！！！");
		Thread t = new Thread() {
			@Override
			public void run() {
				String IP ="10.118.11.217";
				try {
					CamraTest c = new CamraTest();
					if(c.OpenDevice("10.118.11.217") != 0) {;
						System.out.println("打开失败");
						return;
					}
					c.SetResultCallBack("10.118.11.216");
					System.out.println("+++++++++++++++++++++++++++++++++++++++=");
					Thread.sleep(1000 * 60 );
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
		};
		t.start();

	}

}
