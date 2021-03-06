package ca.on.oicr.ws.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@XmlRootElement(name = "sample_type")
@JsonAutoDetect
@JsonSerialize(include = Inclusion.NON_NULL)
public class TypeDto {

	private String name;
	private Integer count;
	private Integer archivedCount;
	private String earliest;
	private String latest;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getEarliest() {
		return earliest;
	}

	public void setEarliest(String earliest) {
		this.earliest = earliest;
	}

	public String getLatest() {
		return latest;
	}

	public void setLatest(String latest) {
		this.latest = latest;
	}

	@XmlElement(name = "archived_count")
	public Integer getArchivedCount() {
		return archivedCount;
	}

	public void setArchivedCount(Integer archivedCount) {
		this.archivedCount = archivedCount;
	}

}
