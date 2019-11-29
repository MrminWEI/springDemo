package com.example.demo.grcloud.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */

public class TreeUtil{
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static  <M extends Tree>  List<M> getResult(List<M> src) {
		List<M> parents = new ArrayList<M>();
		for (M ent : src) {
			if (ent.isRoot()) {
				M result = ent;
				// 次行代码注释，防止前端框架级联选择框渲染时，渲染出空的下级树。
//				result.setChildren(new ArrayList<M>());
				parents.add(result);
			}
		}
		List<M> last = new ArrayList<M>();
		for (M ent : src) {
			if (!ent.isRoot()) {
				last.add(ent);
			}
		}
		buildTree(parents, last);
		return parents;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private  static <M extends Tree> void   buildTree(  List<M> parents, List<M> others) {
		List<M> record = new ArrayList<M>();
		for (Iterator<M> it = parents.iterator(); it.hasNext();) {
			M vi = it.next();
			if (vi.getId() != null) {
				for (Iterator<M> otherIt = others.iterator(); otherIt.hasNext();) {
					M inVi = otherIt.next();
					if (vi.getId().equals(inVi.getPid())) {
						if (null == vi.getChildren()) {
							vi.setChildren(new ArrayList<M>());
						}
						vi.getChildren().add(inVi);
						record.add(inVi);
						otherIt.remove();
					}
				}
			}
		}
		if (others.size() == 0 ) {
			return;
		}else if(parents.size() == 0){
            return;
        } else {
			buildTree(record, others);
		}
	}

}