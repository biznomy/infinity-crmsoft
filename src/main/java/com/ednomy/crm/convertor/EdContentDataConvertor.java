package com.ednomy.crm.convertor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ednomy.crm.entity.EdContentDataEntity;
import com.ednomy.crm.model.EdContentData;
import com.ednomy.crm.model.EdUser;
import com.ednomy.crm.util.CopyUtility;
import com.ednomy.crm.util.DocRepositoryUtil;

@Component
public class EdContentDataConvertor{

	@Autowired
	DocRepositoryUtil docRepositoryUtil;

	@Autowired
	CopyUtility copyUtility;

	@Autowired
	EdContentCollectionConvertor edContentCollectionConvertor;

	@Autowired
	EdUserConvertor edUserConvertor;

	private final String[] ignoreFileds = { "endUser", "edContentCollection"};

	public EdContentData assembler(EdContentDataEntity edContentDataEntity,
			int level) {
		if (edContentDataEntity == null)
			return null;

		EdContentData edContentData = new EdContentData();

		copyUtility
				.copyUtil(edContentDataEntity, edContentData, ignoreFileds);

		/*if (level == 0  && edContentDataEntity.getEdContentCollection() != null) {
			edContentData.setEdContentCollection(edContentCollectionConvertor.assembler(edContentDataEntity.getEdContentCollection(), 1));
		}*/

		if (level == 0 && edContentDataEntity.getEndUser() != null) {
			EdUser clientUser = edUserConvertor.assembler(
					edContentDataEntity.getEndUser(), 1);
			EdUser clientUserResult = new EdUser();
			clientUserResult.setId(clientUser.getId());
			edContentData.setEndUser(clientUserResult);
		}
		
		edContentData.setReceivedDate(edContentDataEntity.getCreatedOn());

		return edContentData;
	}

	public EdContentDataEntity deassembler(EdContentData edContentData,
			int level) {
		if (edContentData == null)
			return null;

		EdContentDataEntity edContentDataEntity = new EdContentDataEntity();

		copyUtility
				.copyUtil(edContentData, edContentDataEntity, ignoreFileds);

		if (level == 0
				&& edContentDataEntity.getEdContentCollection() != null) {
			edContentDataEntity
					.setEdContentCollection(edContentCollectionConvertor
							.deassembler(edContentData.getEdContentCollection(), 0));
		}

		if (level == 0 && edContentData.getEndUser() != null) {
			edContentDataEntity.setEndUser(edUserConvertor.deassembler(
					edContentData.getEndUser(), 0));
		}

		return edContentDataEntity;
	}

	public Set<EdContentData> assembler(
			Set<EdContentDataEntity> edContentDataEntities, int level) {

		Set<EdContentData> edContentDatas = new HashSet<EdContentData>();
		Iterator<EdContentDataEntity> iterator = edContentDataEntities
				.iterator();
		while (iterator.hasNext()) {
			EdContentDataEntity edContentDataEntity = iterator.next();
			edContentDatas.add(assembler(edContentDataEntity, level));
		}
		return edContentDatas;

	}

	public Set<EdContentDataEntity> deassembler(
			Set<EdContentData> edContentDatas, int level) {

		Set<EdContentDataEntity> edContentDataEntities = new HashSet<EdContentDataEntity>();
		Iterator<EdContentData> iterator = edContentDatas.iterator();
		while (iterator.hasNext()) {
			EdContentData edContentData = iterator.next();
			edContentDataEntities.add(deassembler(edContentData, level));
		}
		return edContentDataEntities;

	}
}