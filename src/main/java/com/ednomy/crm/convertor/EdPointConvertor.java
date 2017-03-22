package com.ednomy.crm.convertor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ednomy.crm.entity.EdPointEntity;
import com.ednomy.crm.model.EdPoint;
import com.ednomy.crm.util.CopyUtility;

@Component
public class EdPointConvertor{

	@Autowired
	CopyUtility copyUtility;

	private final String[] ignoreFileds = {};

	public EdPoint assembler(EdPointEntity edPointEntity, int level) {
		if (edPointEntity == null)
			return null;
		EdPoint edPoint = new EdPoint();

		copyUtility.copyUtil(edPointEntity, edPoint, ignoreFileds);
				
		return edPoint;
	}

	public EdPointEntity deassembler(EdPoint edPoint, int level) {
		if (edPoint == null)
			return null;

		EdPointEntity edPointEntity = new EdPointEntity();

		copyUtility.copyUtil(edPoint, edPointEntity, ignoreFileds);

		return edPointEntity;
	}

	public List<EdPoint> assembler(List<EdPointEntity> edPointEntities, int level) {

		List<EdPoint> edPoints = new ArrayList<EdPoint>();
		Iterator<EdPointEntity> iterator = edPointEntities.iterator();
		while (iterator.hasNext()) {
			EdPointEntity edPointEntity = iterator.next();
			edPoints.add(assembler(edPointEntity, level));
		}
		return edPoints;

	}

	public List<EdPointEntity> deassembler(List<EdPoint> edPoints, int level) {

		List<EdPointEntity> edPointEntities = new ArrayList<EdPointEntity>();
		Iterator<EdPoint> iterator = edPoints.iterator();
		while (iterator.hasNext()) {
			EdPoint edPoint = iterator.next();
			edPointEntities.add(deassembler(edPoint, level));
		}
		return edPointEntities;

	}

}
