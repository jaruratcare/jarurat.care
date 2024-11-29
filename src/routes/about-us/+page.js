
export const load = async ({ fetch }) => {
	async function getTeamData() {
		try {
			const response = await fetch('http://localhost:5001/jc/team/getAll/');
			if (!response.ok) {
				throw new Error('Failed to fetch data');
			}
			let memberarray = await response.json();
			return memberarray.members;
		} catch (error) {
			console.log('network error ', error);
		}
	}
	const getAdvisoryBoard = async () => {
		try {
			const response = await fetch(`http://localhost:5001/jc/advisoryBoard/profiles/getAll`);
			if (!response.ok) {
				throw new Error('Failed to fetch data');
			}
			let Advisorydata = await response.json();
			return Advisorydata.profiles
		} catch (error) {
			console.log(`Network error: ${error}`);
		}
	};
	

	return {
		teamData: getTeamData(),
		advisoryBoardData: getAdvisoryBoard(),

	};
}