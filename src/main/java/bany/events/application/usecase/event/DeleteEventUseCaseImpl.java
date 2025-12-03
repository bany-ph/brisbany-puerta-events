package bany.events.application.usecase.event;

import bany.events.domain.port.in.DeleteEventUseCase;
import bany.events.domain.port.out.EventRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteEventUseCaseImpl implements DeleteEventUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public DeleteEventUseCaseImpl(EventRepositoryPort eventRepositoryPort) {
        this.eventRepositoryPort = eventRepositoryPort;
    }

    @Override
    public void deleteById(Long id) {
        eventRepositoryPort.deleteById(id);
    }
}
