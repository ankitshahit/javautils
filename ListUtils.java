package list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ListUtils {

	/**
	 * only add those elements from source which are not present in target
	 * Conditions applied in scenarios where: 1- target == null returns null 2-
	 * Source is null,returns target (Given that target is not null) 3- target
	 * contains all elements of source, return target 4- checks if the target
	 * has that value present, if no. Add that element. Goes till 'n' of source.
	 * Returns target.
	 * 
	 * @param source
	 * @param target
	 * @return
	 */

	public static List<String> copySourceToTargetUniqueElements(
			List<String> source, List<String> target) {
		if (source == null) {
			return new ArrayList<>();
		}

		if (target == null) {
			return uniqueList(source);
		}
		target.addAll(source);
		return uniqueList(target);

	}

	/**
	 * When provided with a java.util.List returns the whole list as unique
	 * where no elements matches with other element present in list. The list is
	 * sorted to in unique and ascending order. Scenarios: 1- source is null,
	 * returns new java.util.ArrayList 2- source is empty, returns source 3-
	 * Source is sorted in ascending order, please make sure that there is no
	 * exception being thrown. returns list in ascending order.
	 * 
	 * 1- Removes null elements explicitly
	 * 
	 * @param source
	 * @return
	 */
	public static List uniqueList(List source) {
		Map<Integer, Object> hashMapForLists = new HashMap();
		if (source == null) {
			return new ArrayList<>();
		}
		if (source.size() <= 0) {
			return source;
		}
		source = removeNullElement(source);
		Iterator elements = source.iterator();
		Object element = new Object();

		while (elements.hasNext()) {
			element = elements.next();
			if(element instanceof Integer || element instanceof Long || element instanceof Double || element instanceof Float || element instanceof Byte){
				hashMapForLists.put(element.hashCode(), element);
				elements.remove();
			}
			if (element instanceof Map) {
				hashMapForLists.put(element.hashCode(), (HashMap) element);
				elements.remove();
			} else if (!(element instanceof Comparable)) {
				hashMapForLists.put(element.hashCode(), element);
				elements.remove();
			}

		}
		Set transformToUniqueElementsInAscendingOrder = new TreeSet<>();
		transformToUniqueElementsInAscendingOrder.addAll(source);
		source.clear();
		source.addAll(transformToUniqueElementsInAscendingOrder);
		for (Entry<Integer, Object> hashMap : hashMapForLists.entrySet()) {
			source.add(hashMap.getValue());
		}
		return source;
		/*
		 * for (Object object : source) { if (object instanceof HashMap) {
		 * System.out.println(object.hashCode());
		 * 
		 * 
		 * System.out.println("instance of hashset found");
		 * source.remove(object);
		 * 
		 * }
		 */// || !(object instanceof Long) ||!(object instanceof Boolean)||
		/*
		 * // !(object instanceof Double) ||!(object instanceof Float) else if
		 * (!(object instanceof Integer)) {
		 * 
		 * } else if (!(object instanceof Long)) {
		 * 
		 * }else if( !(object instanceof Double)){
		 * 
		 * }else if( !(object instanceof Integer)){
		 * 
		 * }else if( !(object instanceof Integer)){
		 * 
		 * }
		 */
		/* } */

	}

	/**
	 * check whether the class mentioned does exist in 'list' -- source, return
	 * true on first instance
	 * 
	 * @param source
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static boolean findInstanceOfClassExists(List source,
			String className) throws ClassNotFoundException {
		if (source == null)
			return false;
		if (className == null)
			return false;
		if (source.size() <= 0)
			return false;
		if (className.isEmpty())
			return false;
		Class targetInstance = Class.forName(className);

		for (Object object : source) {
			if (targetInstance.isInstance(object))
				return true;
		}

		return false;

	}

	/**
	 * Checks whether 'list' has any null element in it. 1- if list itself is
	 * null, return false 2- if list is empty, return false 3- if list contains
	 * any null element, return true Note: Return true on first instance of
	 * finding null element
	 * 
	 * @param source
	 * @return boolean
	 */
	public static boolean containsNullElement(List source) {
		if (source == null)
			return false;
		if (source.isEmpty())
			return false;
		if (source.contains(null))
			return true;
		return false;
	}

	/**
	 * Removes all of the 'null' elements in a list, recursively.
	 * 
	 * @param source
	 * @return source - java.util.List
	 */
	public static List removeNullElement(List source) {
		if (!containsNullElement(source))
			return source;
		while (source.contains(null)) {
			source.remove(null);
		}
		return source;

	}

	public static Object getFirstInstanceOf(List source, String className)
			throws ClassNotFoundException {
		Class targetInstance = Class.forName(className);
		if (findInstanceOfClassExists(source, className)) {
			for (Object object : source) {

				if (targetInstance.isInstance(object))
					return targetInstance.cast(object);

			}
		}
		return null;

	}

	private static Object isInstanceOf(List source, Class targetInstance,
			boolean check) {
		for (Object object : source) {

			if (targetInstance.isInstance(object))
				return targetInstance.cast(object);

		}
		return false;

	}
}
