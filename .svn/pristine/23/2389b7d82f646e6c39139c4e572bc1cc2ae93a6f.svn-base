package com.dianping.activity;

import java.util.List;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapLoadedListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.dianping.R;
import com.dianping.frameworkutils.Data;
import com.dianping.frameworkutils.UTF_8StringRequest;
import com.dianping.frameworkutils.VolleySingleTone;
import com.dianping.model.Goods;
import com.dianping.model.ResponseWrapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.androidannotations.annotations.EActivity;

@EActivity
public class DescoveryActivity extends BaseActivity implements
		LocationSource, AMapLocationListener, OnClickListener,
		 OnMapLoadedListener, OnMarkerClickListener,
		InfoWindowAdapter, OnInfoWindowClickListener {



	AMap aMap;
	MapView mMapView;
	OnLocationChangedListener mListener;
	LocationManagerProxy AMapLocManager = null;
	double lat, lng;

	ImageView iv_back_to_home_page,iv_refresh_goods;
	static int page=1;


	@Override
	protected void onCreate(Bundle arg0) {

		super.onCreate(arg0);
		setContentView(R.layout.layout_descovery);
		if (mMapView == null) {
			mMapView = (MapView) findViewById(R.id.map_view);
			mMapView.onCreate(arg0);
		}
		init();
	}
	@Override
	protected void initVariables() {

	}

	private void init() {

		if (aMap == null) {
			aMap = mMapView.getMap();
			setUpMap();
		}
		iv_back_to_home_page=(ImageView) findViewById(R.id.iv_back_to_home_page);
		iv_refresh_goods = (ImageView) findViewById(R.id.iv_refresh_goods);
		iv_back_to_home_page.setOnClickListener(this);
		iv_refresh_goods.setOnClickListener(this);
	}

	/**
	 * 设置amap的一些属性
	 */
	private void setUpMap() {
		aMap.setLocationSource(this);// 设置位置监听 及位置来源 GPS 还是wifi
		aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认的位置按钮是否显示
		aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位
		aMap.setMapType(AMap.MAP_TYPE_NORMAL);
		aMap.setInfoWindowAdapter(this);
		aMap.setOnInfoWindowClickListener(this);
		aMap.setOnMarkerClickListener(this);
		aMap.setOnMapLoadedListener(this);
		// 设置定位类型 有 定位，跟随，你的面相方向定位
		aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);

	}

	/**
	 * 定位被激活 这个回调给我们传来监听者
	 */

	@Override
	public void activate(OnLocationChangedListener listener) {

		mListener = listener;
		if (AMapLocManager == null) {
			AMapLocManager = LocationManagerProxy.getInstance(this);
			AMapLocManager.requestLocationData(
					LocationProviderProxy.AMapNetwork, 5000, 10, this);
		}
	}

	@Override
	public void deactivate() {
		mListener = null;
		if (AMapLocManager != null) {
			AMapLocManager.removeUpdates(this);
			AMapLocManager.destroy();
			AMapLocManager = null;

		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mMapView.onPause();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mMapView.onSaveInstanceState(outState);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mMapView.onDestroy();
	}

	/**
	 * deprecated
	 */
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onProviderDisabled(String provider) {
		mListener = null;
		if (AMapLocManager != null) {
			AMapLocManager.removeUpdates(this);
			AMapLocManager.destroy();
			AMapLocManager = null;
		}
	}

	/**
	 * 定位成功后调用此接口 们还需要回调回去 否则我们只能定定位一次
	 */
	@Override
	public void onLocationChanged(AMapLocation aLocation) {

		if (mListener != null && aLocation != null) {
			lat = aLocation.getLatitude();
			lng = aLocation.getLongitude();
			mListener.onLocationChanged(aLocation);
			loadData(lat, lng, 10000,1);
			AMapLocManager.removeUpdates(this);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.iv_back_to_home_page:
				deactivate();
				finish();
				break;
			case R.id.iv_refresh_goods:
				page++;
				aMap.clear();
				loadData(lat, lng, 10000,page);
				break;
			default:
				break;
		}
	}

	private void loadData(final double pLat, final double pLng, double radius,int page) {
		String uri = (Data.SHOPS_URI + "lat=" + lat + "&lng=" + lng
				+ "&radius=" + radius+"&page="+page);

		UTF_8StringRequest stringRequest = new UTF_8StringRequest(Request.Method.GET, uri, new Response.Listener<String>() {
			@Override
			public void onResponse(String s) {

				Gson gson = new Gson();

				ResponseWrapper<List<Goods>> result = gson.fromJson(s,
						new TypeToken<ResponseWrapper<List<Goods>>>() {
						}.getType());
				if (result.getResult_State() == 0) {
					Toast.makeText(DescoveryActivity.this, "附近没有商家！", Toast.LENGTH_LONG).show();
					return;
				}

				aMap.animateCamera(CameraUpdateFactory
						.newCameraPosition(new CameraPosition(new LatLng(pLat, pLng), 13,
								0, 30)));
				List<Goods> goodsList = result.getData();
				for (int i = 0; i < goodsList.size(); i++) {
					updateMap(goodsList.get(i));
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError volleyError) {
				Toast.makeText(DescoveryActivity.this,volleyError.getMessage(),Toast.LENGTH_LONG).show();
			}
		});
		VolleySingleTone.getInstance(this).addToRequestQueue(stringRequest);
	}



	private void updateMap(Goods goods) {

		Goods g = goods;
		aMap.addMarker(
				new MarkerOptions()
						.anchor(0.5f, 0.5f)
						.position(
								new LatLng(g.getShop().getLat(), g.getShop()
										.getLng()))
						.icon(BitmapDescriptorFactory
								.fromResource(R.mipmap.pin_green))
						.title("未知商户")
						.snippet(
								"经度：" + g.getShop().getLat() + "纬度："
										+ g.getShop().getLng())).setObject(g);

	}

	@Override
	public void onInfoWindowClick(Marker arg0) {
			ProductDetailActivity_.intent(this).data((Goods)arg0.getObject()).start();
	}

	@Override
	public View getInfoWindow(Marker arg0) {

		return null;
	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		return false;
	}

	@Override
	public void onMapLoaded() {

	}

	@Override
	public View getInfoContents(Marker arg0) {

		return null;
	}



}
