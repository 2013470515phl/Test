package com.phl.blog.service;

import com.phl.blog.dao.CommentRepository;
import com.phl.blog.entity.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Penghanlin-verysix
 * @create 2020-04-07-13:34
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndPraentCommentIsNull(blogId,sort);
        return eachComment(comments);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getPraentComment().getId();
        if(parentCommentId != -1){
            comment.setPraentComment(commentRepository.findById(parentCommentId).get());
        }else {
            comment.setPraentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    /**
     * 循环得到每个顶级的评论节点
     *
     * */
    private List<Comment> eachComment(List<Comment> comments){
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments){
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     *
     * root根节点，blog不为空的对象集合
     *
     * */
    private void combineChildren(List<Comment> comments){
        for (Comment comment : comments){
            List<Comment> replys1 = comment.getReplayComments();
            for (Comment reply1 : replys1){
                //循环迭代找出子代，存放到集合
                recusively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplayComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    private List<Comment> tempReplys = new ArrayList<>();


    /**
     * 递归迭代
     * 循环查找所有子节点评论
     *
     * */
    private void recusively(Comment comment){
        tempReplys.add(comment);//顶级节点添加到临时存放集合
        if(comment.getReplayComments().size() > 0){
            List<Comment> replys = comment.getReplayComments();
            for (Comment reply : replys){
                tempReplys.add(reply);
                if (reply.getReplayComments().size() > 0){
                    recusively(reply);
                }
            }
        }
    }

}
