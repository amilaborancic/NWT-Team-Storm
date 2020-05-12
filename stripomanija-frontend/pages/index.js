import Head from 'next/head';
import styles from "./index.module.css";

export default function Home() {
  return (
    <div className={styles.container}>
      <Head>
        <title>Stripomanija</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>
        Homepage
    </div>
  )
}
