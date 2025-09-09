import { seeder } from 'nestjs-seeder';
import { AppModule } from './app.module';
import { SeedService } from './db/service/SeedService';

seeder({
  imports: [AppModule],
}).run([SeedService]);
