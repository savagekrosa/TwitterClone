package soa.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hashtags")
public class HashtagEntity {
    private String hashtag;
    private List<PostEntity> taggedPosts;

    @Id
    @Column(name = "hashtag")
    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HashtagEntity that = (HashtagEntity) o;

        if (hashtag != null ? !hashtag.equals(that.hashtag) : that.hashtag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return hashtag != null ? hashtag.hashCode() : 0;
    }

    @ManyToMany
    @JoinTable(name = "hashtags", catalog = "Twitter", schema = "public", joinColumns = @JoinColumn(name = "hashtag", referencedColumnName = "hashtag", nullable = false), inverseJoinColumns = @JoinColumn(name = "postid", referencedColumnName = "postid", nullable = false))
    public List<PostEntity> getTaggedPosts() {
        return taggedPosts;
    }

    public void setTaggedPosts(List<PostEntity> taggedPosts) {
        this.taggedPosts = taggedPosts;
    }
}
