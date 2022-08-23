import java.io.IOException;

@Rest(path = "/api/compute")
public class ComputeController {
    private ComputeService computeService;

    public ComputeController(ComputeService computeService) {
        this.computeService = computeService;
    }

    @Post(path = "/add")
    public String add(OperationDTO operationDto) throws IOException {
        computeService.add(new IntOperation(operationDto.x, operationDto.y));
        return "OK";
    }
}
