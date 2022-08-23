import java.io.IOException;

@Rest(path = "/api/compute")
@Component(name = "computeController")
public class StringController {
    @Inject(name = "computeService")
    private ComputeServiceAPI computeService;

    @Post(path = "/add")
    public String add(OperationDTO operationDto) throws IOException {
        computeService.add(new IntOperation(operationDto.x, operationDto.y));
        return "OK";
    }

    @Get(path = "/value")
    public int get() throws IOException {
        return computeService.get();
    }
}
