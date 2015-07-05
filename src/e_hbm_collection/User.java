package e_hbm_collection;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class User {

	private Integer id;
	private String name;
	private Set<String> addressSet;
	private List<String> addressList;
	private String[] addressArray;
	private Map<String, String> addressMap;
	private List<String> addressBag;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", addressSet="
				+ addressSet + ", addressList=" + addressList
				+ ", addressArray=" + Arrays.toString(addressArray)
				+ ", addressMap=" + addressMap + ", addressBag=" + addressBag
				+ "]";
	}
	public String[] getAddressArray() {
		return addressArray;
	}
	public void setAddressArray(String[] addressArray) {
		this.addressArray = addressArray;
	}
	public Map<String, String> getAddressMap() {
		return addressMap;
	}
	public void setAddressMap(Map<String, String> addressMap) {
		this.addressMap = addressMap;
	}
	public List<String> getAddressBag() {
		return addressBag;
	}
	public void setAddressBag(List<String> addressBag) {
		this.addressBag = addressBag;
	}
	public List<String> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<String> getAddressSet() {
		return addressSet;
	}
	public void setAddressSet(Set<String> addressSet) {
		this.addressSet = addressSet;
	}
}
