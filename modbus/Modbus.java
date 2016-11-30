package modbus;

import Exceptions.ModbusException;
import ModbusClient.ModbusClient;

public class Modbus {
	
	private ModbusClient modbusClient;
	private static final int IP_ADDRESS_REGISTER = 0; // from 1 to 4
	private static final int RESSOURCE_TEMPERATURE_REGISTER = 10;
	private static final int VALUE_TEMPERATURE_REGISTER = 15;
//	private int[] ipAddress = new int[4];
	
	public Modbus(ModbusClient modbusClient) {
		this.modbusClient = modbusClient;
	}
	
	public ModbusClient getModbusClient() {
		return modbusClient;
	}

	public void setModbusClient(ModbusClient modbusClient) {
		this.modbusClient = modbusClient;
	}

	public int[] readFromRegister(int startingAddress, int quantity) 
			throws ModbusException, java.net.UnknownHostException, 
			java.net.SocketException, java.io.IOException{
		int[] values = modbusClient.ReadHoldingRegisters(startingAddress, quantity);
		for (int i : values) {
			System.out.println(i);
		}	
		return values;
	}
	
	public void writeToMultipleRegisters(int startingAddress, int[] values, Modbus reader) 
			throws ModbusException, java.net.UnknownHostException, 
			java.net.SocketException, java.io.IOException{
		modbusClient.WriteMultipleRegisters(startingAddress, values);
		reader.readFromRegister(startingAddress, values.length);
	}
	
	public void writeToSingleRegister(int startingAddress, int value, Modbus reader)
			throws ModbusException, java.net.UnknownHostException, 
			java.net.SocketException, java.io.IOException{
		modbusClient.WriteSingleRegister(startingAddress, value);
		reader.readFromRegister(startingAddress, 1);
	}
	
	public int getThingDescription(int[] ipAddress, Modbus gateway) 
			throws ModbusException, java.net.UnknownHostException, 
			java.net.SocketException, java.io.IOException{
		this.writeToMultipleRegisters(IP_ADDRESS_REGISTER, ipAddress, gateway);
		
		return gateway.sendThingDescription();
		// change returned type to support returned ressources of the TD
	}
	
	public float getProperty(int ressource, Modbus gateway) 
			throws ModbusException, java.net.UnknownHostException, 
			java.net.SocketException, java.io.IOException{
			int[] ipAddress = this.readFromRegister(0, 4);
			this.writeToSingleRegister(VALUE_TEMPERATURE_REGISTER, 1, gateway); // DO MAPPING !!!!
			return gateway.sendProperty();
	}
	
	public int sendThingDescription()
			throws ModbusException, java.net.UnknownHostException, 
			java.net.SocketException, java.io.IOException{
		
		// call CoAP
		return 0;		
	}
	
	public float sendProperty(){
		// call CoAP
		return (float) 1.1;
	}	
}