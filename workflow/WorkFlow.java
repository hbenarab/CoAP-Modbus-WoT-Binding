package workflow;

import ModbusClient.ModbusClient;
import modbus.GUI;
import modbus.Modbus;

public class WorkFlow {
	public static void main(String[] args){
		
		ModbusClient modbusClient = new ModbusClient("127.0.0.1",502);
		try {
			modbusClient.Connect();
			
			Modbus modbusClientGUI = new Modbus( modbusClient);
			Modbus modbusClientGateway = new Modbus(modbusClient);
			
			int[] ipAddress = {192, 168, 1, 18};
			
			/*
			 * Workflow example: get thing description
			 */
			modbusClientGUI.writeToSingleRegister(0, 1, modbusClientGateway);
			modbusClientGUI.writeToMultipleRegisters(10, ipAddress, modbusClientGateway);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

//		ModbusClient modbusClient = new ModbusClient("127.0.0.1",502);
//		try
//		{
//			int[] ipAddress = {192, 168, 1, 18};
//			modbusClient.Connect();
//			modbusClient.WriteSingleCoil(0, true);
//			modbusClient.WriteSingleRegister(0, 1234);
//			modbusClient.WriteMultipleRegisters(11, ipAddress);			
//			modbusClient.WriteMultipleRegisters(11, ModbusClient.ConvertFloatToTwoRegisters((float) 123.56));
//			System.out.println(modbusClient.ReadCoils(0, 1)[0]);
//			System.out.println(modbusClient.ReadHoldingRegisters(0, 1)[0]);
//			System.out.println(ModbusClient.ConvertRegistersToFloat(modbusClient.ReadHoldingRegisters(11, 2)));
//		}
//		catch (Exception e)
//		{		
//		}
	}
}