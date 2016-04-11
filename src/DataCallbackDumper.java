import org.openscada.opc.lib.da.DataCallback;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.ItemState;


public class DataCallbackDumper implements DataCallback{

	@Override
	public void changed(Item item, ItemState arg1) {
		String value = arg1.getValue().toString();
		System.out.println(item.getId()+"==========>"+value.substring(2, value.length()-2));
		
	}
	

}
