package org.panel;

import android.app.Service;

public abstract class Comunicacion {
	
	public abstract void startServer();
	public abstract void _startService();
	public abstract void updateCoordenadas();
	public abstract void stopServer();

}
