package javafish.clients.opc;

import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;
import javafish.clients.opc.exception.UnableAddGroupException;

public class Jeasyopctest {
	public static void main(String[] args) throws Exception, UnableAddGroupException {
		//第一步，初始化
		//把配置文件javafish/clients/opc/JCustomOpc.properties、JCustomOpc.dll放到classpath
		System.out.println(System.getProperty("java.class.path"));
		JOpc.coInitialize();
		//第二步，建立一个JOpc对象，三个参数，分别是OpcServer的IP，Server的name，还有JOpc的name
		JOpc jopc = new JOpc("192.168.18.142", "National Instruments.NIOPCServers.V5", "a");
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
		OpcItem item2 = jopc.synchReadItem(group, item);
		System.out.println(String.valueOf(item2.getValue()));
		//第六步赋值，并同步至服务器
		//item.setValue(new Variant(1));
		//jopc.synchWriteItem(group, item);
		//最后，该释放的全释放掉
		jopc.unregisterItem(group, item);
		jopc.unregisterGroup(group);
		JOpc.coUninitialize();
		System.exit(0);

	}
	
}
