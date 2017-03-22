package com.ednomy.crm.convertor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ednomy.crm.entity.EdUserEntity;
import com.ednomy.crm.model.EdUser;
import com.ednomy.crm.util.CopyUtility;
import com.ednomy.crm.util.DocRepositoryUtil;

@Component
public class EdUserConvertor {

	@Autowired
	DocRepositoryUtil docRepositoryUtil;

	@Autowired
	CopyUtility copyUtility;

	@Autowired
	EdContentCollectionConvertor edContentCollectionConvertor;

	@Autowired
	EdContentDataConvertor edContentDataConvertor;

	private final String[] ignoreFileds = {"clientUser", "edContentDatas",
			"edContentCollections", "status1", "status2", "status3", "authToken"};

	public EdUser assembler(EdUserEntity edUserEntity, int level) {
		if (edUserEntity == null)
			return null;
		EdUser edUser = new EdUser();

		copyUtility.copyUtil(edUserEntity, edUser, ignoreFileds);
		
		if (level == 0 && edUserEntity.getClientUser() != null) {
			EdUser clientUser = assembler(edUserEntity.getClientUser(), 1);
			EdUser clientUserResult = new EdUser();
			clientUserResult.setId(clientUser.getId());
			edUser.setClientUser(clientUserResult);
		}

		edUser.setStatus1(edUserEntity.getStatus1());
		edUser.setStatus2(edUserEntity.getStatus2());
		edUser.setStatus3(edUserEntity.getStatus3());
		edUser.setReceivedDate(edUserEntity.getCreatedOn());
		
		return edUser;
	}

	public EdUserEntity deassembler(EdUser edUser, int level) {
		if (edUser == null)
			return null;

		EdUserEntity edUserEntity = new EdUserEntity();

		copyUtility.copyUtil(edUser, edUserEntity, ignoreFileds);

		if (level == 0 && !edUser.getEdContentCollections().isEmpty()) {
			edUserEntity.setEdContentCollections(edContentCollectionConvertor
							.deassembler(edUser.getEdContentCollections(), 1));
		}

		if (level == 0 && edUser.getClientUser() != null) {
			edUserEntity
					.setClientUser(deassembler(edUser.getClientUser(), 1));
		}

		if (level == 0 && !edUserEntity.getEdContentDatas().isEmpty()) {
			edUserEntity.setEdContentDatas(edContentDataConvertor
					.deassembler(edUser.getEdContentDatas(), 1));
		}
		
		if (edUser.getStatus1() != null) {
			edUserEntity.setStatus1(edUser.getStatus1());
		}
		if (edUser.getStatus2() != null) {
			edUserEntity.setStatus2(edUser.getStatus2());
		}
		if (edUser.getStatus3() != null) {
			edUserEntity.setStatus3(edUser.getStatus3());
		}

		return edUserEntity;
	}

	public Set<EdUser> assembler(Set<EdUserEntity> edUserEntities, int level) {

		Set<EdUser> edUsers = new HashSet<EdUser>();
		Iterator<EdUserEntity> iterator = edUserEntities.iterator();
		while (iterator.hasNext()) {
			EdUserEntity edUserEntity = iterator.next();
			edUsers.add(assembler(edUserEntity, level));
		}
		return edUsers;

	}

	public Set<EdUserEntity> deassembler(Set<EdUser> edUsers, int level) {

		Set<EdUserEntity> edUserEntities = new HashSet<EdUserEntity>();
		Iterator<EdUser> iterator = edUsers.iterator();
		while (iterator.hasNext()) {
			EdUser edUser = iterator.next();
			edUserEntities.add(deassembler(edUser, level));
		}
		return edUserEntities;

	}

}
