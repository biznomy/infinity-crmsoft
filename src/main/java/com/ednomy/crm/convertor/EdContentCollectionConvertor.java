package com.ednomy.crm.convertor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ednomy.crm.entity.EdContentCollectionEntity;
import com.ednomy.crm.model.EdContentCollection;
import com.ednomy.crm.model.EdUser;
import com.ednomy.crm.util.CopyUtility;
import com.ednomy.crm.util.DocRepositoryUtil;

@Component
public class EdContentCollectionConvertor{

	@Autowired
	DocRepositoryUtil docRepositoryUtil;

	@Autowired
	CopyUtility copyUtility;

	@Autowired
	EdContentDataConvertor edContentDataConvertor;

	@Autowired
	EdUserConvertor edUserConvertor;
	
	Logger log  = LoggerFactory.getLogger(getClass()); 

	private final String[] ignoreFileds = { "clientUser", "edContentDatas"};

	public EdContentCollection assembler(
			EdContentCollectionEntity edContentCollectionEntity, int level) {
		if (edContentCollectionEntity == null)
			return null;
		EdContentCollection edContentCollection = new EdContentCollection();

		try {
			copyUtility.copyUtil(edContentCollectionEntity, edContentCollection, ignoreFileds);
		} catch (FatalBeanException exception) {
			System.err.println(exception.getMessage());
		}

		if (level == 0 && edContentCollectionEntity.getClientUser() != null) {
			
			EdUser clientUser = edUserConvertor.assembler(
					edContentCollectionEntity.getClientUser(), 1);
			EdUser clientUserResult = new EdUser();
			clientUserResult.setId(clientUser.getId());
			edContentCollection.setClientUser(clientUserResult);
		}

		return edContentCollection;
	}

	public EdContentCollectionEntity deassembler(
			EdContentCollection edContentCollection, int level) {
		if (edContentCollection == null)
			return null;

		EdContentCollectionEntity edContentCollectionEntity = new EdContentCollectionEntity();

		copyUtility.copyUtil(edContentCollection, edContentCollectionEntity,
				ignoreFileds);

		if (level == 0 && !edContentCollection.getEdContentDatas().isEmpty()) {
			edContentCollectionEntity
					.setEdContentDatas(edContentDataConvertor.deassembler(
							edContentCollection.getEdContentDatas(), 0));
		}

		if (level == 0 && edContentCollection.getClientUser() != null) {
			edContentCollectionEntity.setClientUser(edUserConvertor
					.deassembler(edContentCollection.getClientUser(), 0));
		}
		
		return edContentCollectionEntity;
	}

	public Set<EdContentCollection> assembler(
			Set<EdContentCollectionEntity> edContentCollectionEntities,
			int level) {

		Set<EdContentCollection> edContentCollections = new HashSet<EdContentCollection>();
		Iterator<EdContentCollectionEntity> iterator = edContentCollectionEntities
				.iterator();
		while (iterator.hasNext()) {
			EdContentCollectionEntity edContentCollectionEntity = iterator
					.next();
			edContentCollections.add(assembler(edContentCollectionEntity,
					level));
		}
		return edContentCollections;

	}

	public Set<EdContentCollectionEntity> deassembler(
			Set<EdContentCollection> edContentCollections, int level) {

		Set<EdContentCollectionEntity> edContentCollectionEntities = new HashSet<EdContentCollectionEntity>();
		Iterator<EdContentCollection> iterator = edContentCollections
				.iterator();
		while (iterator.hasNext()) {
			EdContentCollection edContentCollection = iterator.next();
			edContentCollectionEntities.add(deassembler(edContentCollection,
					level));
		}
		return edContentCollectionEntities;

	}
}
