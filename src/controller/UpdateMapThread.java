package controller;

import visualization.MapComponent;


public class UpdateMapThread implements Runnable {
private int n;
private boolean m;
private static MapComponent map;
	

	public UpdateMapThread(int n, boolean m, MapComponent map) {
		this.n = n;
		this.m = m;
		UpdateMapThread.map = map;
	}
	

	@Override
	public void run() {
		synchronized(map) {
			try {
				map.updateRoadTypes(n,m);
			}
			catch(Exception e) {
			}
		}
		
	}

}
