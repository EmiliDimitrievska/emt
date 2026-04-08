import { Link } from 'react-router-dom';
function CountryPage() {
    return (
        <div>
            <h1>COUNTRIES PAGE WORKS</h1>
            <Link to="/countries">Go to Countries</Link>
        </div>
    );
}

export default CountryPage;