package javafish.clients.opc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import com.digi.smartph.SupportMeterReadingBean;

import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;
import javafish.clients.opc.exception.UnableAddGroupException;


public class GetOpcData {
	private String url = "172.16.60.11";
	private String OpcServerName = "AKE_OPC_SERVER";
	private String waterGroupName = "waterGroupName";
	private String elecGroupName = "elecGroupName";
	
	public List<SupportMeterReadingBean> getElecFee(List<SupportMeterReadingBean> devList) throws Exception{
		
//		System.out.println("./lib/"+new File("./lib/").getAbsolutePath());
		JOpc.coInitialize();
		
		JOpc jopc1 = new JOpc(url, OpcServerName, "elecOpc");
		jopc1.connect();
		//OpcGroup group1 = new OpcGroup(elecGroupName, true, 500, 0.0f);
		
		JOpc jopc2 = new JOpc(url, OpcServerName, "waterOpc");
		jopc2.connect();
		//OpcGroup group2 = new OpcGroup(waterGroupName, true, 500, 0.0f);
		
//		String itemName = "DEGB.03001.CurValue";
//		OpcItem item = new OpcItem(itemName, true, "");
//		group1.addItem(item);
//		jopc1.addGroup(group1);
//		jopc1.registerGroup(group1);
//		jopc1.registerItem(group1, item);
//		OpcItem item2 = jopc1.synchReadItem(group1, item);
//		System.out.println("电量==="+String.valueOf(item2.getValue()));
		
		for(int i=0;i<devList.size();i++){
			String elecDevId = String.valueOf(devList.get(i).getElec_no());
			String waterDevId = String.valueOf(devList.get(i).getWater_no());
			System.out.println(i+"电表NO="+elecDevId);
			if(!("".equals(elecDevId) || elecDevId == null || "null".equals(elecDevId))){
				OpcGroup group1 = new OpcGroup(elecGroupName+i, true, 500, 0.0f);
				String itemName = "DEGB."+elecDevId+".CurValue";
				System.out.println(itemName);
				OpcItem item = new OpcItem(itemName, true, "");
				group1.addItem(item);
				jopc1.addGroup(group1);
				jopc1.registerGroup(group1);
				jopc1.registerItem(group1, item);
				OpcItem item2 = jopc1.synchReadItem(group1, item);
				System.out.println("电量==="+item2.getValue());
				devList.get(i).setElec(Double.parseDouble(String.valueOf(item2.getValue())));
				jopc1.unregisterItem(group1, item);
				jopc1.unregisterGroup(group1);
			}
			if(!("".equals(waterDevId) || waterDevId == null || "null".equals(waterDevId))){
				OpcGroup group2 = new OpcGroup(waterGroupName+i, true, 500, 0.0f);
				String itemName = "WateM."+waterDevId+".CurValue";
				OpcItem item = new OpcItem(itemName, true, "");
				group2.addItem(item);
				jopc2.addGroup(group2);
				jopc2.registerGroup(group2);
				jopc2.registerItem(group2, item);
				OpcItem item2 = jopc1.synchReadItem(group2, item);
				System.out.println("水量==="+item2.getValue());
				devList.get(i).setWater(Double.parseDouble(String.valueOf(item2.getValue())));
				jopc2.unregisterItem(group2, item);
				jopc2.unregisterGroup(group2);
			}
		}
		JOpc.coUninitialize();

		
		return devList;//单位千瓦时
		
	}
	public static void main(String[] args) throws Exception, UnableAddGroupException {
		//第一步，初始化
		//把配置文件javafish/clients/opc/JCustomOpc.properties、JCustomOpc.dll放到classpath
		JOpc.coInitialize();
		//第二步，建立一个JOpc对象，三个参数，分别是OpcServer的IP，Server的name，还有JOpc的name
		JOpc jopc = new JOpc("192.168.18.65", "Matrikon.OPC.Simulation.1", "jopc1");
		//第三步，建立连接
		jopc.connect();
		//第四步，新建一个OPC的group和item，并把item加到group中
		OpcGroup group = new OpcGroup("Group0", true, 500, 0.0f);
		OpcItem item = new OpcItem("Random.Int4", true, "");
		group.addItem(item);
		//第五步，注册group，item
		jopc.addGroup(group);
		jopc.registerGroup(group);
		jopc.registerItem(group, item);
		for(int i =0;i<100;i++) {
			OpcItem item2 = jopc.synchReadItem(group, item);
			System.out.println(String.valueOf(item2.getValue()));
			Thread.sleep(100);
		}	
		//第六步赋值，并同步至服务器
		//item.setValue(new Variant(1));
		//jopc.synchWriteItem(group, item);
		//最后，该释放的全释放掉
		jopc.unregisterItem(group, item);
		jopc.unregisterGroup(group);
		JOpc.coUninitialize();

	}
	
	public String getOpcData(SupportMeterReadingBean smrb) throws UnknownHostException, IOException {
		String host = url; // 要连接的服务端IP地址
		int port = 9111; // 要连接的服务端对应的监听端口
		// 与服务端建立连接
		Socket client = new Socket(host, port);
		// 建立连接后就可以往服务端写数据了
		Writer writer = new OutputStreamWriter(client.getOutputStream());
		writer.write(smrb.getElec_no()+";"+smrb.getWater_no());
		writer.write("eof\n");
		writer.flush();
		// 写完以后进行读操作
		BufferedReader br = new BufferedReader(new InputStreamReader(
				client.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String temp;
		int index;
		while ((temp = br.readLine()) != null) {
			if ((index = temp.indexOf("eof")) != -1) {
				sb.append(temp.substring(0, index));
				break;
			}
			sb.append(temp);
		}
		System.out.println("from server: " + sb);
		writer.close();
		br.close();
		client.close();
		return sb.toString();
	}
	
}
