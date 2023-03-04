package com.music.gu.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName v_music_relation
 */
@TableName(value ="v_music_relation")
@Data
public class VMusicRelation implements Serializable {
    /**
     *
     */
    @TableField(value = "id")
    private Integer id;

    /**
     *
     */
    @TableField(value = "list_id")
    private Integer listId;

    /**
     *
     */
    @TableField(value = "list_title")
    private String listTitle;

    /**
     *
     */
    @TableField(value = "artist_name")
    private String artistName;

    /**
     *
     */
    @TableField(value = "music_id")
    private Integer musicId;

    /**
     *
     */
    @TableField(value = "music_name")
    private String musicName;

    /**
     *
     */
    @TableField(value = "music_album")
    private String musicAlbum;

    /**
     *
     */
    @TableField(value = "music_lyrics")
    private String musicLyrics;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        VMusicRelation other = (VMusicRelation) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getListId() == null ? other.getListId() == null : this.getListId().equals(other.getListId()))
            && (this.getListTitle() == null ? other.getListTitle() == null : this.getListTitle().equals(other.getListTitle()))
            && (this.getArtistName() == null ? other.getArtistName() == null : this.getArtistName().equals(other.getArtistName()))
            && (this.getMusicId() == null ? other.getMusicId() == null : this.getMusicId().equals(other.getMusicId()))
            && (this.getMusicName() == null ? other.getMusicName() == null : this.getMusicName().equals(other.getMusicName()))
            && (this.getMusicAlbum() == null ? other.getMusicAlbum() == null : this.getMusicAlbum().equals(other.getMusicAlbum()))
            && (this.getMusicLyrics() == null ? other.getMusicLyrics() == null : this.getMusicLyrics().equals(other.getMusicLyrics()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getListId() == null) ? 0 : getListId().hashCode());
        result = prime * result + ((getListTitle() == null) ? 0 : getListTitle().hashCode());
        result = prime * result + ((getArtistName() == null) ? 0 : getArtistName().hashCode());
        result = prime * result + ((getMusicId() == null) ? 0 : getMusicId().hashCode());
        result = prime * result + ((getMusicName() == null) ? 0 : getMusicName().hashCode());
        result = prime * result + ((getMusicAlbum() == null) ? 0 : getMusicAlbum().hashCode());
        result = prime * result + ((getMusicLyrics() == null) ? 0 : getMusicLyrics().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", list_id=").append(listId);
        sb.append(", list_title=").append(listTitle);
        sb.append(", artist_name=").append(artistName);
        sb.append(", music_id=").append(musicId);
        sb.append(", music_name=").append(musicName);
        sb.append(", music_album=").append(musicAlbum);
        sb.append(", music_lyrics=").append(musicLyrics);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
