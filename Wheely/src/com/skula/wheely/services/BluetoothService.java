package com.skula.wheely.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class BluetoothService {
	private BluetoothDevice device = null;
	private BluetoothSocket socket = null;
	private InputStream receiveStream = null;
	private OutputStream sendStream = null;

	public BluetoothService(String macAddress) {
		Set<BluetoothDevice> setpairedDevices = BluetoothAdapter
				.getDefaultAdapter().getBondedDevices();

		for (BluetoothDevice btd : setpairedDevices) {
			if (btd.getAddress().equals(macAddress)) {
				device = btd;
				try {
					socket = device
							.createRfcommSocketToServiceRecord(UUID
									.fromString("00001101-0000-1000-8000-00805F9B34FB"));
					receiveStream = socket.getInputStream();
					sendStream = socket.getOutputStream();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	public BluetoothService(BluetoothDevice device) {
		this.device = device;
		try {
			socket = device.createRfcommSocketToServiceRecord(UUID
					.fromString("00001101-0000-1000-8000-00805F9B34FB"));
			receiveStream = socket.getInputStream();
			sendStream = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendData(String data) {
		sendData(data, false);
	}

	public void sendData(String data, boolean deleteScheduledData) {
		try {
			sendStream.write(data.getBytes());
			sendStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void connect() {
		new Thread() {
			@Override
			public void run() {
				try {
					socket.connect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BluetoothDevice getDevice() {
		return device;
	}
}
