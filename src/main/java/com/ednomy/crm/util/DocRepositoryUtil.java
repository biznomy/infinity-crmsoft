package com.ednomy.crm.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.collection.internal.PersistentBag;
import org.hibernate.collection.internal.PersistentSet;
import org.springframework.stereotype.Component;

@Component
public class DocRepositoryUtil {

	@PersistenceContext
	EntityManager entityManager;

	public boolean isAvaliable(Object obj) {
		if (obj instanceof PersistentBag) {
			PersistentBag persistentBag = (PersistentBag) obj;
			try {
				if (!persistentBag.isEmpty()) {
					return true;
				}
			} catch (Exception exception) {
			}
			return persistentBag.isDirectlyAccessible();
		}

		if (obj instanceof PersistentSet) {
			PersistentSet persistentSet = (PersistentSet) obj;
			try {
				if (!persistentSet.isEmpty()) {
					return true;
				}
			} catch (Exception exception) {
			}
			return persistentSet.isDirectlyAccessible();
		}
		return false;
	}	
}
