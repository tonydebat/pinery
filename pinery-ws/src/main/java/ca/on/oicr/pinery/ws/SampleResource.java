package ca.on.oicr.pinery.ws;

import java.net.URI;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ca.on.oicr.pinery.api.Sample;
import ca.on.oicr.pinery.api.SampleProject;
import ca.on.oicr.pinery.service.SampleService;
import ca.on.oicr.ws.dto.Dtos;
import ca.on.oicr.ws.dto.SampleDto;
import ca.on.oicr.ws.dto.SampleProjectDto;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Component
@Path("/")
public class SampleResource {

	private static final Logger log = LoggerFactory.getLogger(SampleResource.class);

	@Context
	private UriInfo uriInfo;

	@Autowired
	private SampleService sampleService;

	@GET
	@Produces({ "application/json" })
	@Path("/samples")
	public List<SampleDto> getSamples(@QueryParam("archived") Boolean archived,
			@QueryParam("project") Set<String> projects, @QueryParam("type") Set<String> types,
			@QueryParam("before") String before, @QueryParam("after") String after) {
		log.debug("archived = [{}].", archived);
		for (String type : types) {
			log.debug("type={}", type);
		}
		DateTime beforeDateTime = null;
		DateTime afterDateTime = null;
		try {
			if (before != null && !before.equals("")) {
				beforeDateTime = DateTime.parse(before);
			}
			if (after != null && !after.equals("")) {
				afterDateTime = DateTime.parse(after);
			}
		} catch (IllegalArgumentException e) {
			throw new BadRequestException(
					"Invalid date format in parameter [before] or [after]. Use ISO8601 formatting. " + e.getMessage(),
					e);
		}
		List<Sample> samples = sampleService.getSamples(archived, projects, types, beforeDateTime, afterDateTime);
		if (samples.isEmpty()) {
			throw new NotFoundException("", Response.noContent().status(Status.NOT_FOUND).build());
		}
		List<SampleDto> result = Lists.newArrayList();
		final URI baseUri = uriInfo.getBaseUriBuilder().path("sample").build();
		for (Sample sample : samples) {
			SampleDto dto = Dtos.asDto(sample);
			dto.setUrl(baseUri + "/" + dto.getId().toString());
			if (sample.getChildren() != null && !sample.getChildren().isEmpty()) {
				dto.setChildren(Sets.<String> newHashSet());
				for (Integer childId : sample.getChildren()) {
					dto.getChildren().add(baseUri + "/" + childId);
				}
			}
			addParents(sample, dto);
			addUsers(sample, dto);
			// if (sample.getParents() != null &&
			// !sample.getParents().isEmpty()) {
			// dto.setParents(Sets.<String> newHashSet());
			// for (Integer parentId : sample.getParents()) {
			// dto.getParents().add(baseUri + "/" + parentId);
			// }
			// }
			result.add(dto);
		}
		return result;
	}

	@GET
	@Produces({ "application/json" })
	@Path("/sample/{id}")
	public SampleDto getSample(@PathParam("id") Integer id) {
		Sample sample = sampleService.getSample(id);
		SampleDto dto = Dtos.asDto(sample);
		final URI uri = uriInfo.getAbsolutePathBuilder().build();
		dto.setUrl(uri.toString());
		final URI baseUri = uriInfo.getBaseUriBuilder().path("sample").build();
		if (sample.getChildren() != null && !sample.getChildren().isEmpty()) {
			dto.setChildren(Sets.<String> newHashSet());
			for (Integer childId : sample.getChildren()) {
				dto.getChildren().add(baseUri + "/" + childId);
			}
		}
		addParents(sample, dto);
		addUsers(sample, dto);
		return dto;
	}

	@GET
	@Produces({ "application/json" })
	@Path("/samples/projects")
	public List<SampleProjectDto> getSampleProjects() {
		List<SampleProject> projects = sampleService.getSampleProjects();
		List<SampleProjectDto> result = Lists.newArrayList();
		for (SampleProject sampleProject : projects) {
			result.add(Dtos.asDto(sampleProject));
		}
		Collections.sort(result, new Comparator<SampleProjectDto>() {

			@Override
			public int compare(SampleProjectDto o1, SampleProjectDto o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return result;
	}

	private void addParents(Sample sample, SampleDto dto) {
		// Keep a temporary Set of parents. We'll check to see if any of these
		// are null. (Indicates root of tree.)
		Set<String> parents = Sets.newHashSet();
		if (sample.getParents() != null && !sample.getParents().isEmpty()) {
			for (Integer parentId : sample.getParents()) {
				// Ignore null, as this idicates the root of the tree.
				if (parentId != null) {
					parents.add(parentId.toString());
				}
			}
		}
		if (!parents.isEmpty()) {
			dto.setParents(Sets.<String> newHashSet());
			final URI baseUri = uriInfo.getBaseUriBuilder().path("sample").build();
			for (String parentId : parents) {
				dto.getParents().add(baseUri + "/" + parentId);
			}
		}
	}

	private void addUsers(Sample sample, SampleDto dto) {
		final URI baseUri = uriInfo.getBaseUriBuilder().path("user/").build();
		if(sample.getCreatedById() != null) {
			dto.setCreatedByUrl(baseUri + sample.getCreatedById().toString());
		}
		if (sample.getModifiedById() != null) {
			dto.setModifiedByUrl(baseUri + sample.getModifiedById().toString());
		}
	}

}
