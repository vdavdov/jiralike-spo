package by.vdavdov.bugtracking.tree;

import by.vdavdov.common.HasIdAndParentId;

import java.util.List;

public interface ITreeNode<T extends HasIdAndParentId, R extends ITreeNode<T, R>> {
    List<R> subNodes();
}
