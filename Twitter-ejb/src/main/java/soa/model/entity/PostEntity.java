package soa.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NamedQuery(name = "PostEntity.findAll", query = "select post from PostEntity post")
@Entity
@Table(name = "posts")
public class PostEntity {
    private int postId;
    private String content;
    private Timestamp creationdate;
    private List<CommentEntity> comments;
    private List<HashtagEntity> hashtags;
    private UserEntity author;

    public PostEntity() {
        comments = new ArrayList<>();
        hashtags = new ArrayList<>();
        creationdate = new Timestamp(new Date().getTime());
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postid")
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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
    public Timestamp getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Timestamp creationdate) {
        this.creationdate = creationdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostEntity that = (PostEntity) o;

        if (postId != that.postId) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (creationdate != null ? !creationdate.equals(that.creationdate) : that.creationdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = postId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (creationdate != null ? creationdate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "commentedPost")
    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "hashtagsposts",
            joinColumns = @JoinColumn(name = "postid", referencedColumnName = "postid", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "hashtag", referencedColumnName = "hashtag", nullable = false))
    public List<HashtagEntity> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<HashtagEntity> hashtags) {
        this.hashtags = hashtags;
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
