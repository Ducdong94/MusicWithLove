package com.management.service.entity;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

 
@Entity
@Table(name = "song")
public class Song {
	@Id
	private String id;
	private String title;
	private String artists_names;
	private String code;
	private String duration;
	private String link;
	private String thumbnail;
	@NotNull
	private BigInteger time;
//	@ManyToMany(mappedBy = "songs")
//	List<Category> categories;
//	@ManyToMany(mappedBy = "playlistSongs")
//	List<Playlist> playlists;

	public Song() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtists_names() {
		return artists_names;
	}

	public void setArtists_names(String artists_names) {
		this.artists_names = artists_names;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public BigInteger getTime() {
		return time;
	}

	public void setTime(BigInteger time) {
		this.time = time;
	}

//	public List<Category> getCategories() {
//		return categories;
//	}
//
//	public void setCategories(List<Category> categories) {
//		this.categories = categories;
//	}

//	public List<Playlist> getPlaylists() {
//		return playlists;
//	}
//
//	public void setPlaylists(List<Playlist> playlists) {
//		this.playlists = playlists;
//	}

//	@Override
//	public String toString() {
//		return "Song [id=" + id + ", title=" + title + ", artists_names=" + artists_names + ", code=" + code
//				+ ", duration=" + duration + ", link=" + link + ", thumbnail=" + thumbnail + ", time=" + time
//				+ ", categories=" + categories + ", playlists=" + playlists + "]";
//	}
}
