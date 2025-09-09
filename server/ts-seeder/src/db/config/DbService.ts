import { Injectable, OnModuleInit, OnModuleDestroy } from '@nestjs/common';
import { Pool } from 'pg';

@Injectable()
export class DbService implements OnModuleInit, OnModuleDestroy {
  private pool: Pool;

  async onModuleInit() {
    this.pool = new Pool({
      host: 'ep-calm-scene-a7lwkrso-pooler.ap-southeast-2.aws.neon.tech',
      port: 5432,
      database: 'neondb',
      user: 'neondb_owner',
      password: 'npg_0ihkJHUy6fDt',
      ssl: {
        rejectUnauthorized: false,
      },
    });
    await this.pool.query('SELECT 1');
  }

  async onModuleDestroy() {
    await this.pool.end();
  }

  async query<T = any>(sql: string, params?: any[]): Promise<T[]> {
    const client = await this.pool.connect();
    try {
      const result = await client.query(sql, params);
      return result.rows;
    } finally {
      client.release();
    }
  }
}
