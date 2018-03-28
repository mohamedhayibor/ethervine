function automine() {
	setInterval(function () {
		if (!eth.mining && (txpool.status.pending || txpool.status.queued)) {
			console.log("Mining....");
			miner.start();
		} else if (eth.mining) {
			console.log("Mining stopped...");
		}
	}, 10 * 1000); // 10s
}