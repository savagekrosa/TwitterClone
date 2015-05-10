package soa.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
public class CommentEntity {
    private int commentId;
    private String content;
    private Timestamp creationDate;
    private PostEntity commentedPost;
    private UserEntity author;

    @Id
    @Column(name = "commentid")
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "creationdate")
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentEntity that = (CommentEntity) o;

        if (commentId != that.commentId) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "postid", referencedColumnName = "postid", nullable = false)
    public PostEntity getCommentedPost() {
        return commentedPost;
    }

    public void setCommentedPost(PostEntity commentedPost) {
        this.commentedPost = commentedPost;
    }

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "userid", nullable = false)
    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }
}
