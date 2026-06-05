package igor.projeto.tp1.resource;

import java.util.List;

import igor.projeto.tp1.dto.admin.TenisPerformanceRequestAdminDTO;
import igor.projeto.tp1.dto.admin.TenisPerformanceResponseAdminDTO;
import igor.projeto.tp1.mapper.TenisPerformanceMapper;
import igor.projeto.tp1.model.TenisPerformance;
import igor.projeto.tp1.service.TenisPerformanceAdminService;
import jakarta.inject.Inject;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/tenis-performance")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RolesAllowed({"ADMIN", "FUNCIONARIO"})

@Tag(name = "Tenis Performance Admin", description = "Endpoints para administração de tênis de performance")
public class TenisPerformanceAdminResource {

    @Inject
    TenisPerformanceAdminService service;

    @GET
    @Operation(summary = "Listar todos os tênis", description = "Retorna uma lista com todos os tênis de performance cadastrados")
    public List<TenisPerformanceResponseAdminDTO> buscarTodos() {
        return service.findAll()
                .stream()
                .map(e -> TenisPerformanceMapper.toResponseDTO(e))
                .toList();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Buscar tênis por ID", description = "Retorna um tênis específico pelo seu ID")
    public TenisPerformanceResponseAdminDTO buscarPeloId(@PathParam("id") Long id) {
        return TenisPerformanceMapper.toResponseDTO(service.findById(id));
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Deletar tênis", description = "Remove um tênis de performance do sistema")
    public void deletar(@PathParam("id") Long id) {
        service.delete(id);
    }

    @POST
    @Operation(summary = "Criar novo Tenis Performance", description = "Cria um novo registro de tênis de performance")
    @RequestBody(
        description = "Dados do tênis a ser criado",
        content = @Content(schema = @Schema(implementation = TenisPerformanceRequestAdminDTO.class), mediaType = MediaType.APPLICATION_JSON)
    )
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Tênis criado com sucesso",
            content = @Content(schema = @Schema(implementation = TenisPerformanceResponseAdminDTO.class))),
        @APIResponse(responseCode = "400", description = "Dados inválidos")
    })
    public TenisPerformanceResponseAdminDTO incluir(TenisPerformanceRequestAdminDTO dto) {
        TenisPerformance tenis = service.create(TenisPerformanceMapper.toEntity(dto), dto.url());
        return TenisPerformanceMapper.toResponseDTO(tenis);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Atualizar tênis", description = "Atualiza os dados de um tênis de performance")
    public void alterar(@PathParam("id") Long id, TenisPerformanceRequestAdminDTO dto) {
        service.update(id, TenisPerformanceMapper.toEntity(dto), dto.url());
    }
}
