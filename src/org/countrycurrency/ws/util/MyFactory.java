package org.countrycurrency.ws.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author jmsanchez
 *
 */

public class MyFactory<KeyObject, ValueObject> {

	private Map<KeyObject, ValueObject> objectsMap = new HashMap<KeyObject, ValueObject>();

	/**
	 * Añade la key como clave y el value como valor.
	 * 
	 * @param key
	 *            KeyObject
	 * @param value
	 *            ValueObject
	 * @return
	 * 			String
	 */
	public String add(KeyObject key, ValueObject value) {
		objectsMap.put(key, value);
		return key + " insertado correctamente";
	}

	/**
	 * Actualiza la entrada del elemento key.
	 * 
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	public void update(KeyObject key, ValueObject value) throws Exception {
		if (objectsMap.containsKey(key)) {
			objectsMap.put(key, value);
		} else {
			throw new Exception("El elemento " + key + " no existe.");
		}
	}

	/**
	 * Elimina la entrada que tiene <code>key</code> como clave.
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String remove(KeyObject key) throws Exception {
		if (objectsMap.containsKey(key)) {
			objectsMap.remove(key);
			return key + " eliminado correctamente";
		} else {
			throw new Exception("El elemento " + key + " no existe.");
		}
	}

	/**
	 * Obtiene el valor que esta referenciado por key.
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public ValueObject getValueByKey(KeyObject key) throws Exception {
		if (objectsMap.containsKey(key)) {
			return objectsMap.get(key);
		} else {
			throw new Exception("El elemento " + key + " no existe.");
		}
	}

	/**
	 * Obtiene todos los valores del Map (sin repetir).
	 * 
	 * @return
	 */
	public Set<ValueObject> getAllValues() {
		return new HashSet<ValueObject>(objectsMap.values());
	}

	/**
	 * Obtiene todos las claves del Map.
	 * 
	 * @return
	 */
	public List<KeyObject> getAllKeys() {
		return new ArrayList<KeyObject>(objectsMap.keySet());
	}

	/**
	 * Indica si el Map contiene la clave.
	 * 
	 * @return
	 */
	public boolean contains(KeyObject key) {
		if (objectsMap.get(key) == null) {
			return false;
		} else {
			return true;
		}
	}
}