package org.panel; 

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem; 

//mensaje de alerta
//import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//Commit 2

/**
 * Una clase para dibujar los Overlays del mapa, junto a los dialogos de introduccion de datos.
 * @version 1.0, 10/06/04
 * @author Jon Arribas, Javier Martin
 */
public class ItemizedOverlay extends com.google.android.maps.ItemizedOverlay {
	
	 
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	GeoPoint punto;
	List<Overlay> mapOverlays;
	Drawable drawable;
	ItemizedOverlay itemizedOverlay;
	 boolean OK = false;
	
	 /** 
	  * Constructor de la clase ItemizedOverlay
	  */
	public ItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		// TODO Auto-generated constructor stub
	}

	 /** 
     * Crea el Overlay en la posicion indicada del array
     * @return el valor del overlay.
     */
	protected OverlayItem createItem(int i) {
	  return mOverlays.get(i);
	}

	 /** 
     * Metodo para saber el tama�o del array del Overlay.
     * @return el tama�o del array overlay.
     */
	public int size() {
		// TODO Auto-generated method stub
		return mOverlays.size();
	}
	/** 
     * Metodo para a�adir y mostrar el overlay en pantalla.
     * @param El overlay
     */
	public void addOverlay(OverlayItem overlay) {
	    mOverlays.add(overlay);
	    populate();
	}
	
	/** 
     * Metodo que al hacer click en pantalla dibuja el marcador(overlay) e inicia los dialogos
     * para insertar el radio y dibujarlo.
     * @param point El punto que hemos clickado en la pantalla
     * @param mapView El mapa, para obtener el contexto
     */
	public boolean onTap(GeoPoint point, MapView mapView)
	{
		
		
				
	    Context contexto = mapView.getContext(); 
	    
	   
	    
	    showAddDialog(contexto, point, mapView);
	    
	    //mapOverlays.add(new OverlayMapa(punto));
	    
	    /*mapOverlays = mapView.getOverlays();
	    mapOverlays.clear();
	    drawable = contexto.getResources().getDrawable(R.drawable.androidmarker);
        itemizedOverlay = new ItemizedOverlay(drawable);
        punto = new GeoPoint(point.getLatitudeE6(),point.getLongitudeE6());
        OverlayItem overlayitem = new OverlayItem(punto, "", "");
        
        
        itemizedOverlay.addOverlay(overlayitem);
      
        mapOverlays.add(itemizedOverlay);
        Log.d("Estoy fuera de mapoverlays", "");*/
	    
        //mapOverlays.add(new OverlayMapa(punto));
	    
	    
	    /*String msg = "Lat: " + point.getLatitudeE6()/1E6 + " - " +
	        "Lon: " + point.getLongitudeE6()/1E6;
	 
	    Toast toast = Toast.makeText(contexto, msg, Toast.LENGTH_SHORT);
	    toast.show();
	   
	    mapOverlays = mapView.getOverlays();
	    mapOverlays.clear();
	    drawable = contexto.getResources().getDrawable(R.drawable.androidmarker);
        itemizedOverlay = new ItemizedOverlay(drawable);
        punto = new GeoPoint(point.getLatitudeE6(),point.getLongitudeE6());
        OverlayItem overlayitem = new OverlayItem(punto, "", "");
        
        
        itemizedOverlay.addOverlay(overlayitem);
      
        mapOverlays.add(itemizedOverlay);
        
        mapOverlays.add(new OverlayMapa(punto));  */
        
	    return true;
	}
	
	/** 
     * Metodo que lanza el dialogo de comprobacion del centro
     * @param contexto El contexto del mapa
     * @param point El punto que hemos clickado en pantalla
     * @param mapView El mapa
     */
	private void showAddDialog(final Context contexto, final GeoPoint point, final MapView mapView) {

		final String TAG = "test";
		final Dialog loginDialog = new Dialog(contexto);
		loginDialog.getWindow().setFlags(
		WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
		WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		loginDialog.setTitle("Centro del alcance");

		LayoutInflater li = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View dialogView = li.inflate(R.layout.add_dailog, null);
		loginDialog.setContentView(dialogView);

		loginDialog.show();

		Button addButton = (Button) dialogView.findViewById(R.id.add_button);
		Button cancelButton = (Button) dialogView
		.findViewById(R.id.cancel_button);

		/** 
	     * Metodo que se ocupa del Listener del boton a�adir(OK) del dialogo de comprobacion
	     */
		addButton.setOnClickListener(new OnClickListener() {
		// @Override
		public void onClick(View v) {

		//Toast.makeText(contexto, "Please enter Number.",
		//Toast.LENGTH_LONG).show();
			loginDialog.dismiss();
			 String msg = "Lat: " + point.getLatitudeE6()/1E6 + " - " +
				        "Lon: " + point.getLongitudeE6()/1E6;
				 
				    Toast toast = Toast.makeText(contexto, msg, Toast.LENGTH_SHORT);
				    toast.show();
				    
				    mapOverlays = mapView.getOverlays();
				    mapOverlays.clear();
				    drawable = contexto.getResources().getDrawable(R.drawable.androidmarker);
			        itemizedOverlay = new ItemizedOverlay(drawable);
			        punto = new GeoPoint(point.getLatitudeE6(),point.getLongitudeE6());
			        OverlayItem overlayitem = new OverlayItem(punto, "", "");
			         
			        
			        itemizedOverlay.addOverlay(overlayitem);
			      
			        mapOverlays.add(itemizedOverlay);
				   
				    //mapOverlays.add(new OverlayMapa(punto));
				    mapView.invalidate();
				    
				   
				    
				    /*try
					{
					    OutputStreamWriter fout=
					        new OutputStreamWriter(
					            contexto.openFileOutput("notas.txt", contexto.MODE_PRIVATE));
					 
					    fout.write("Latitud.:"+punto.getLatitudeE6()+"\n");
					    fout.write("Longitud.:"+punto.getLongitudeE6()+"\n");
					    fout.close();
					}
					catch (Exception ex)
					{
					    Log.e("Ficheros", "Error al escribir fichero a memoria interna");
					}*/
			       
			       
				    
				   //loginDialog.show();
				    showRadiusDialog(contexto, punto, mapView);
				  
				    
				   /* mapOverlays = mapView.getOverlays();
				    mapOverlays.clear();
				    drawable = contexto.getResources().getDrawable(R.drawable.androidmarker);
			        itemizedOverlay = new ItemizedOverlay(drawable);
			        punto = new GeoPoint(point.getLatitudeE6(),point.getLongitudeE6());
			        OverlayItem overlayitem = new OverlayItem(punto, "", "");
			        
			        
			        itemizedOverlay.addOverlay(overlayitem);
			      
			        mapOverlays.add(itemizedOverlay);
			        Log.d("Estoy fuera de mapoverlays", "");*/
			        //mapOverlays.add(new OverlayMapa(punto));  
			        
			        
			        
		}
		});
		/** 
	     * Metodo que se ocupa del Listener del boton cancelar del dialogo de comprobacion
	     */
		cancelButton.setOnClickListener(new OnClickListener() {
		// @Override
		public void onClick(View v) {
			loginDialog.dismiss();
			
		
		}
		});

		}
	
	/** 
     * Metodo lanza el dialogo de introduccion del radio, y lo escribe en un fichero de texto
     * @param contexto El contexto del mapa
     * @param point El punto que hemos clickado en pantalla
     * @param mapView El mapa
     */
	private void showRadiusDialog(final Context contexto, final GeoPoint point, final MapView mapView) {

		final String TAG = "test";
		final Dialog radiusDialog = new Dialog(contexto);
		radiusDialog.getWindow().setFlags(
		WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
		WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		radiusDialog.setTitle("Configuracion del Radio del alcance");

		LayoutInflater li = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View dialogView = li.inflate(R.layout.rad_dialog, null);
		radiusDialog.setContentView(dialogView);

		radiusDialog.show();

		Button addButton = (Button) dialogView.findViewById(R.id.add_button_radio);
		Button cancelButton = (Button) dialogView.findViewById(R.id.cancel_button_radio);
		/** 
	     * Metodo que se ocupa del Listener del boton a�adir(OK) del dialogo del radio
	     */
		addButton.setOnClickListener(new OnClickListener() {
		// @Override
		public void onClick(View v) {
			EditText radio;
			radio=(EditText) dialogView.findViewById(R.id.uname_id);
			
		
			radiusDialog.dismiss();
			 /*String msg = "Lat: " + point.getLatitudeE6()/1E6 + " - " +
				        "Lon: " + point.getLongitudeE6()/1E6;
				 
				    Toast toast = Toast.makeText(contexto, msg, Toast.LENGTH_SHORT);
				    toast.show();*/
			/*try {
	            OutputStreamWriter archivo = new OutputStreamWriter(contexto.openFileOutput("notas.txt",Activity.MODE_PRIVATE));
	            archivo.write(radio.toString());
	            archivo.flush();
	            archivo.close();            
	        }catch (IOException e)
	        {
	        }*/
			
			try
			{
			    OutputStreamWriter fout=
			        new OutputStreamWriter(
			            contexto.openFileOutput("radio.txt", contexto.MODE_PRIVATE));
			 
			    //fout.write("Latitud.:"+punto.getLatitudeE6()+"\n");
			    //fout.write("Longitud.:"+punto.getLongitudeE6()+"\n");
			    //fout.write("\nRadio:"+Integer.parseInt(radio.getText().toString()));
			    fout.write(punto.getLatitudeE6()+" "+punto.getLongitudeE6()+" "+Integer.parseInt(radio.getText().toString()));
			    fout.close();
			}
			catch (Exception ex)
			{
			    Log.e("Ficheros", "Error al escribir fichero a memoria interna");
			}
			
				    
					mapOverlays.add(new OverlayMapa(punto,Integer.parseInt(radio.getText().toString())));
				   
				    //mapOverlays.add(new OverlayMapa(punto));
				    mapView.invalidate();
				    
		}
		});
		
		/** 
	     * Metodo que se ocupa del Listener del boton cancelar del dialogo del radio
	     */

		cancelButton.setOnClickListener(new OnClickListener() {
		// @Override
		public void onClick(View v) {
			radiusDialog.dismiss();
			
		
		}
		});

		}
	
	


	
}
